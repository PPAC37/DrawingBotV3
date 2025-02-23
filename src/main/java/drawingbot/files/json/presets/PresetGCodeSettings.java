package drawingbot.files.json.presets;

import com.google.gson.JsonElement;
import drawingbot.files.json.AbstractJsonData;
import drawingbot.files.json.PresetType;
import drawingbot.registry.Register;

import java.util.HashMap;

public class PresetGCodeSettings extends AbstractJsonData {

    public PresetGCodeSettings() {
        super();
    }

    public PresetGCodeSettings(HashMap<String, JsonElement> settingList) {
        super(settingList);
    }

    @Override
    public PresetType getPresetType() {
        return Register.PRESET_TYPE_GCODE_SETTINGS;
    }
}
