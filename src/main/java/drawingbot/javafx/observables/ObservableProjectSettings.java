package drawingbot.javafx.observables;

import drawingbot.DrawingBotV3;
import drawingbot.files.FileUtils;
import drawingbot.files.json.presets.PresetProjectSettings;
import drawingbot.image.BufferedImageLoader;
import drawingbot.javafx.GenericPreset;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class ObservableProjectSettings {

    public SimpleObjectProperty<ImageView> imageView;
    public SimpleObjectProperty<WritableImage> thumbnail;
    public SimpleStringProperty userDefinedName;
    public SimpleStringProperty date;
    public SimpleStringProperty pfm;
    public SimpleStringProperty file;
    public SimpleObjectProperty<GenericPreset<PresetProjectSettings>> preset;

    public ObservableProjectSettings(GenericPreset<PresetProjectSettings> preset, boolean isSubProject){
        this.imageView = new SimpleObjectProperty<>(new ImageView());
        this.thumbnail = new SimpleObjectProperty<>(null);
        this.userDefinedName = new SimpleStringProperty(preset.data.name);
        this.date = new SimpleStringProperty(preset.data.timeStamp);
        this.pfm = new SimpleStringProperty(preset.data.pfmSettings.presetSubType);
        this.file = new SimpleStringProperty(preset.data.imagePath);
        this.preset = new SimpleObjectProperty<>(preset);

        this.userDefinedName.addListener((observable, oldValue, newValue) -> preset.data.name = newValue);

        this.imageView.get().imageProperty().bind(thumbnail);
        this.imageView.get().preserveRatioProperty().set(true);
        this.imageView.get().fitWidthProperty().bind(DrawingBotV3.INSTANCE.controller.versionThumbColumn.widthProperty());

        BufferedImageLoader loader = new BufferedImageLoader(FileUtils.getUserThumbnailDirectory() + preset.data.thumbnailID + ".jpg", false);
        DrawingBotV3.INSTANCE.startTask(DrawingBotV3.INSTANCE.backgroundService, loader);
        loader.setOnSucceeded(e -> thumbnail.set(SwingFXUtils.toFXImage(loader.getValue(), null)));

        preset.data.isSubProject = isSubProject;
    }

}
