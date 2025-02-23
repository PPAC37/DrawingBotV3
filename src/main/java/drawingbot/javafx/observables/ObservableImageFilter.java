package drawingbot.javafx.observables;

import drawingbot.DrawingBotV3;
import drawingbot.javafx.GenericFactory;
import drawingbot.javafx.GenericSetting;
import drawingbot.registry.MasterRegistry;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public class ObservableImageFilter {

    public final SimpleBooleanProperty enable;
    public final SimpleStringProperty name;
    public final GenericFactory<BufferedImageOp> filterFactory;
    public final ObservableList<GenericSetting<?, ?>> filterSettings;
    public final SimpleStringProperty settingsString; //settings as a string

    public final SimpleBooleanProperty dirty;
    public final SimpleObjectProperty<BufferedImage> cached;

    public ObservableImageFilter(GenericFactory<BufferedImageOp> filterFactory) {
        this(true, filterFactory);
    }

    public ObservableImageFilter(boolean enable, GenericFactory<BufferedImageOp> filterFactory) {
        this(enable, filterFactory.getName(), filterFactory, MasterRegistry.INSTANCE.createObservableImageFilterSettings(filterFactory));
    }

    public ObservableImageFilter(ObservableImageFilter duplicate) {
        this(duplicate.enable.get(), duplicate.name.get(), duplicate.filterFactory, GenericSetting.copy(duplicate.filterSettings, FXCollections.observableArrayList()));
        GenericSetting.applySettings(duplicate.filterSettings, this.filterSettings);
    }

    public ObservableImageFilter(boolean enable, String name, GenericFactory<BufferedImageOp> filterFactory, ObservableList<GenericSetting<?, ?>> filterSettings) {
        this.enable = new SimpleBooleanProperty(enable);
        this.name = new SimpleStringProperty(name);
        this.filterFactory = filterFactory;
        this.filterSettings = filterSettings;
        this.settingsString = new SimpleStringProperty(filterSettings.toString());

        this.filterSettings.forEach(s -> s.addListener((observable, oldValue, newValue) -> onSettingChanged()));
        this.enable.addListener((observable, oldValue, newValue) -> DrawingBotV3.INSTANCE.onImageFilterChanged(this));
        //changes to filter settings are called from the FXController

        this.dirty = new SimpleBooleanProperty(true);
        this.cached = new SimpleObjectProperty<>(null);
    }

    public void onSettingChanged() {
        ///actually updating the image filter rendering is done elsewhere, this shouldn't always change as values do
        this.settingsString.set(filterSettings.toString());
    }
}
