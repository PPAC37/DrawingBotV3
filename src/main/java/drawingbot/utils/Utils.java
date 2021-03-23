package drawingbot.utils;

import drawingbot.files.exporters.GCodeExporter;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    public static Random random = new Random();
    public static NumberFormat defaultNF = NumberFormat.getNumberInstance();
    public static String URL_GITHUB_REPO = "https://github.com/SonarSonic/DrawingBotV3";
    public static String URL_GITHUB_WIKI = URL_GITHUB_REPO + "/wiki";
    public static String URL_GITHUB_PFM_DOCS = URL_GITHUB_WIKI + "/Advanced-PFM-Settings";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    public static NumberFormat gCodeNF;

    public static String formatGCode(float f){
        if(gCodeNF == null){
            gCodeNF = NumberFormat.getNumberInstance();
            gCodeNF.setGroupingUsed(false);
            gCodeNF.setMinimumIntegerDigits(0);
            gCodeNF.setMinimumFractionDigits(GCodeExporter.gcode_decimals);
            gCodeNF.setMaximumFractionDigits(GCodeExporter.gcode_decimals);
        }
        return gCodeNF.format(f);
    }

    public static String capitalize(String name) {
        if (name != null && name.length() != 0) {
            char[] chars = name.toLowerCase().toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars).replace('_', ' ');
        } else {
            return name;
        }
    }

    public static String getDateAndTime(){
        Date date = new Date(System.currentTimeMillis());
        return dateFormat.format(date);
    }

    public static long clamp(long value, long min, long max){
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static float clamp(float value, float min, float max){
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static double clamp(double value, double min, double max){
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static int clamp(int value, int min, int max){
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }


    public static int mapInt(int value, int istart, int istop, int ostart, int ostop) {
        return (int) mapFloat(value, istart, istop, ostart, ostop);
    }

    public static float mapFloat(float value, float istart, float istop, float ostart, float ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }

    public static double mapDouble(double value, double istart, double istop, double ostart, double ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }


    public static double decrease(double x, double mult) {
        if (mult == 0D) {
            return x;
        }
        double mod = x % mult;
        return x - mult + mod;
    }

    public static double increase(double x, double mult) {
        if (mult == 0D) {
            return x;
        }
        double mod = x % mult;
        return x + mult - mod;
    }

    public static double floorTo(double x, double mult) {
        return mult == 0D ? x : x - (x % mult);
    }

    public static double ceilTo(double x, double mult) {
        if (mult == 0D) return x;
        double mod = x % mult;
        return mod == 0D ? x : x + mult - mod;
    }

    public static double roundToMultiple(double x, double mult) {
        if (mult == 0D) {
            return x;
        }
        double mod = x % mult;
        return mod >= mult/2D ? x + mult - mod : x - mod;
    }

    public static float roundToMultiple(float x, float mult) {
        if (mult == 0F) {
            return x;
        }
        float mod = x % mult;
        return mod >= mult/2F ? x + mult - mod : x - mod;
    }

    public static double roundToPrecision(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
