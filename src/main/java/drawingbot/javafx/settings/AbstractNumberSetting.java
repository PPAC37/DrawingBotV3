package drawingbot.javafx.settings;

import drawingbot.javafx.GenericSetting;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.util.StringConverter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class AbstractNumberSetting<C, V extends Number> extends GenericSetting<C, V> {

    public boolean isRanged;
    public V minValue;
    public V maxValue;

    public V safeMinValue;
    public V safeMaxValue;

    public V majorTick;
    public boolean snapToTicks;

    protected AbstractNumberSetting(AbstractNumberSetting<C, V> toCopy, V newValue) {
        super(toCopy, newValue);
        this.isRanged = true;
        this.minValue = toCopy.minValue;
        this.maxValue = toCopy.maxValue;
        this.safeMinValue = toCopy.safeMinValue;
        this.safeMaxValue = toCopy.safeMaxValue;
        this.majorTick = toCopy.majorTick;
        this.snapToTicks = toCopy.snapToTicks;
        this.setRandomiser(toCopy.randomiser);
    }

    public AbstractNumberSetting(Class<C> clazz, String category, String settingName, V defaultValue, StringConverter<V> stringConverter, BiConsumer<C, V> setter) {
        super(clazz, category, settingName, defaultValue, stringConverter, value -> value, setter);
        this.isRanged = false;
    }

    public AbstractNumberSetting(Class<C> clazz, String category, String settingName, V defaultValue, V minValue, V maxValue, StringConverter<V> stringConverter, Function<ThreadLocalRandom, V> randomiser, Function<V, V> validator, BiConsumer<C, V> setter) {
        super(clazz, category, settingName, defaultValue, stringConverter, validator, setter);
        this.isRanged = true;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.safeMinValue = minValue;
        this.safeMaxValue = maxValue;
        this.setRandomiser(randomiser);
    }

    public abstract void setRandomizerRange(V safeMinValue, V safeMaxValue);

    public AbstractNumberSetting<C, V> setSafeRange(V safeMinValue, V safeMaxValue){
        this.safeMinValue = safeMinValue;
        this.safeMaxValue = safeMaxValue;
        this.setRandomizerRange(safeMinValue, safeMaxValue);
        return this;
    }

    public AbstractNumberSetting<C, V> setMajorTick(V majorTick){
        this.majorTick = majorTick;
        return this;
    }

    public AbstractNumberSetting<C, V> setSnapToTicks(boolean snapToTicks){
        this.snapToTicks = snapToTicks;
        return this;
    }

    @Override
    public Node createJavaFXNode(boolean label) {
        if(!isRanged){
            return getEditableTextField();
        }

        //graphics
        Slider slider = new Slider();
        slider.setMin(safeMinValue.doubleValue());
        slider.setMax(safeMaxValue.doubleValue());
        slider.setValue(value.getValue().doubleValue());

        //bindings
        slider.valueProperty().addListener((observable, oldValue, newValue) -> setValue(fromNumber(newValue)));

        value.addListener((observable, oldValue, newValue) -> {
            slider.setValue(newValue.doubleValue());
        });

        if(label){
            //show markings
            slider.setMajorTickUnit(majorTick == null ? Math.min(Integer.MAX_VALUE, Math.abs(safeMaxValue.doubleValue()-safeMinValue.doubleValue())) : majorTick.doubleValue());
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setSnapToTicks(snapToTicks);
        }

        return slider;
    }

    public abstract V fromNumber(Number number);
}