package drawingbot.files.exporters;

import drawingbot.DrawingBotV3;
import drawingbot.api.IGeometryFilter;
import drawingbot.files.ConfigFileHandler;
import drawingbot.files.ExportTask;
import drawingbot.image.blend.BlendComposite;
import drawingbot.image.blend.EnumBlendMode;
import drawingbot.utils.UnitsLength;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageExporter {

    public static boolean useAlphaChannelOnRaster(ExportTask exportTask){
        return exportTask.extension.equals(".png");
    }

    public static int getRasterWidth(ExportTask exportTask, boolean isVideo){
        int width= (int) exportTask.exportResolution.getScaledWidth();
        if(isVideo && width % 2 == 1){
            width-=1;
        }
        return width;
    }

    public static int getRasterHeight(ExportTask exportTask, boolean isVideo){
        int height = (int)exportTask.exportResolution.getScaledHeight();

        if(isVideo && height % 2 == 1){
            height-=1;
        }

        return height;
    }

    public static BufferedImage createFreshBufferedImage(ExportTask exportTask, boolean isVideo){
        int width = getRasterWidth(exportTask, isVideo);
        int height = getRasterHeight(exportTask, isVideo);
        boolean useAlphaChannel = useAlphaChannelOnRaster(exportTask);
        return new BufferedImage(width, height, useAlphaChannel ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
    }

    public static Graphics2D createFreshGraphics2D(ExportTask exportTask, BufferedImage image, boolean isVideo){
        int width = getRasterWidth(exportTask, isVideo);
        int height = getRasterHeight(exportTask, isVideo);
        Graphics2D graphics = image.createGraphics();
        if(!useAlphaChannelOnRaster(exportTask)){
            Graphics2DExporter.drawBackground(exportTask, graphics, width, height);
        }
        setBlendMode(exportTask, graphics);
        return graphics;
    }

    public static void setBlendMode(ExportTask exportTask, Graphics2D graphics2D){
        EnumBlendMode blendMode = DrawingBotV3.INSTANCE.blendMode.get();
        if(blendMode != EnumBlendMode.NORMAL){
            graphics2D.setComposite(new BlendComposite(blendMode));
        }
    }

    public static void exportImage(ExportTask exportTask, File saveLocation) {
        if (!exportTask.exportResolution.useOriginalSizing() && exportTask.exportResolution.finalPrintScaleX == 1 && exportTask.exportResolution.drawingArea.optimiseForPrint.get() && exportTask.exportResolution.drawingArea.targetPenWidth.get() > 0 ){
            int DPI = (int)ConfigFileHandler.getApplicationSettings().exportDPI;
            int exportWidth = (int)Math.ceil((exportTask.exportResolution.printPageWidth/ UnitsLength.INCHES.convertToMM) * DPI);
            int exportHeight = (int)Math.ceil((exportTask.exportResolution.printPageHeight / UnitsLength.INCHES.convertToMM) * DPI);
            exportTask.exportResolution.changePrintResolution(exportWidth, exportHeight);
        }
        int width = (int)exportTask.exportResolution.getScaledWidth();
        int height = (int)exportTask.exportResolution.getScaledHeight();

        BufferedImage image = createFreshBufferedImage(exportTask, false);
        Graphics2D graphics = createFreshGraphics2D(exportTask, image, false);

        Graphics2DExporter.preDraw(exportTask, graphics);
        Graphics2DExporter.drawGeometries(exportTask, graphics, IGeometryFilter.BYPASS_FILTER);
        Graphics2DExporter.postDraw(exportTask, graphics);

        try {
            ImageIO.write(image, exportTask.extension.substring(1), saveLocation);
        } catch (IOException e) {
            exportTask.setError(e.getMessage());
            e.printStackTrace();
        }
    }
}
