package drawingbot.javafx;

import drawingbot.DrawingBotV3;
import drawingbot.api.Hooks;
import drawingbot.utils.EnumAlignment;
import drawingbot.utils.EnumRotation;
import drawingbot.files.ConfigFileHandler;
import drawingbot.files.exporters.GCodeBuilder;
import drawingbot.files.exporters.GCodeExporter;
import drawingbot.files.json.presets.PresetGCodeSettings;
import drawingbot.registry.Register;
import drawingbot.utils.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

public class FXExportController {

    public void initialize(){
        Hooks.runHook(Hooks.FX_EXPORT_CONTROLLER_PRE_INIT, this);

        initPathOptimisationPane();
        initSVGSettingsPane();
        initGCodeSettingsPane();
        initImageSequencePane();
        initHPGLSettingsPane();

        DrawingBotV3.INSTANCE.controller.exportSettingsStage.setOnHidden(e -> ConfigFileHandler.getApplicationSettings().markDirty());

        DrawingBotV3.INSTANCE.controller.exportSettingsStage.setOnShown(e -> updateImageSequenceStats());


        Hooks.runHook(Hooks.FX_EXPORT_CONTROLLER_POST_INIT, this);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ////PATH OPTIMISATION

    public CheckBox checkBoxEnableOptimisation = null;
    public Pane paneOptimisationControls = null;

    public CheckBox checkBoxSimplify = null;
    public TextField textFieldSimplifyTolerance = null;
    public ChoiceBox<UnitsLength> choiceBoxSimplifyUnits = null;

    public CheckBox checkBoxMerge = null;
    public TextField textFieldMergeTolerance = null;
    public ChoiceBox<UnitsLength> choiceBoxMergeUnits = null;

    public CheckBox checkBoxFilter = null;
    public TextField textFieldFilterTolerance = null;
    public ChoiceBox<UnitsLength> choiceBoxFilterUnits = null;

    public CheckBox checkBoxSort = null;
    public TextField textFieldSortTolerance = null;
    public ChoiceBox<UnitsLength> choiceBoxSortUnits = null;

    public void initPathOptimisationPane(){
        checkBoxEnableOptimisation.setSelected(ConfigFileHandler.getApplicationSettings().pathOptimisationEnabled);
        checkBoxEnableOptimisation.selectedProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().pathOptimisationEnabled = newValue);

        paneOptimisationControls.disableProperty().bind(checkBoxEnableOptimisation.selectedProperty().not());

        ///simplify

        checkBoxSimplify.setSelected(ConfigFileHandler.getApplicationSettings().lineSimplifyEnabled);
        checkBoxSimplify.selectedProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineSimplifyEnabled = newValue);

        textFieldSimplifyTolerance.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), 0.1F));
        textFieldSimplifyTolerance.setText("" + ConfigFileHandler.getApplicationSettings().lineSimplifyTolerance);
        textFieldSimplifyTolerance.textProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineSimplifyTolerance = Float.parseFloat(newValue));

        choiceBoxSimplifyUnits.setValue(ConfigFileHandler.getApplicationSettings().lineSimplifyUnits);
        choiceBoxSimplifyUnits.setItems(FXCollections.observableArrayList(UnitsLength.values()));
        choiceBoxSimplifyUnits.valueProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineSimplifyUnits = newValue);

        ///merge

        checkBoxMerge.setSelected(ConfigFileHandler.getApplicationSettings().lineMergingEnabled);
        checkBoxMerge.selectedProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineMergingEnabled = newValue);

        textFieldMergeTolerance.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), 0.5F));
        textFieldMergeTolerance.setText("" + ConfigFileHandler.getApplicationSettings().lineMergingTolerance);
        textFieldMergeTolerance.textProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineMergingTolerance = Float.parseFloat(newValue));

        choiceBoxMergeUnits.setValue(ConfigFileHandler.getApplicationSettings().lineMergingUnits);
        choiceBoxMergeUnits.setItems(FXCollections.observableArrayList(UnitsLength.values()));
        choiceBoxMergeUnits.valueProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineMergingUnits = newValue);

        ///filter

        checkBoxFilter.setSelected(ConfigFileHandler.getApplicationSettings().lineFilteringEnabled);
        checkBoxFilter.selectedProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineFilteringEnabled = newValue);

        textFieldFilterTolerance.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), 0.5F));
        textFieldFilterTolerance.setText("" + ConfigFileHandler.getApplicationSettings().lineFilteringTolerance);
        textFieldFilterTolerance.textProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineFilteringTolerance = Float.parseFloat(newValue));

        choiceBoxFilterUnits.setValue(ConfigFileHandler.getApplicationSettings().lineFilteringUnits);
        choiceBoxFilterUnits.setItems(FXCollections.observableArrayList(UnitsLength.values()));
        choiceBoxFilterUnits.valueProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineFilteringUnits = newValue);

        //sort

        checkBoxSort.setSelected(ConfigFileHandler.getApplicationSettings().lineSortingEnabled);
        checkBoxSort.selectedProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineSortingEnabled = newValue);

        textFieldSortTolerance.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), 1F));
        textFieldSortTolerance.setText("" + ConfigFileHandler.getApplicationSettings().lineSortingTolerance);
        textFieldSortTolerance.textProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineSortingTolerance = Float.parseFloat(newValue));

        choiceBoxSortUnits.setValue(ConfigFileHandler.getApplicationSettings().lineSortingUnits);
        choiceBoxSortUnits.setItems(FXCollections.observableArrayList(UnitsLength.values()));
        choiceBoxSortUnits.valueProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().lineSortingUnits = newValue);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ////SVG SETTINGS

    public CheckBox checkBoxEnableSVGLayerNaming = null;

    public void initSVGSettingsPane(){
        checkBoxEnableSVGLayerNaming.setSelected(ConfigFileHandler.getApplicationSettings().svgLayerRenaming);
        checkBoxEnableSVGLayerNaming.selectedProperty().addListener((observable, oldValue, newValue) -> ConfigFileHandler.getApplicationSettings().svgLayerRenaming = newValue);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ////GCODE SETTINGS

    public AnchorPane anchorPaneGCodeSettings = null;

    public ComboBox<GenericPreset<PresetGCodeSettings>> comboBoxGCodePreset = null;
    public MenuButton menuButtonGCodePresets = null;

    public TextField textFieldOffsetX = null;
    public TextField textFieldOffsetY = null;
    public ChoiceBox<UnitsLength> choiceBoxGCodeUnits = null;
    public TextField textFieldGCodeCurveFlatness = null;
    public CheckBox checkBoxGCodeEnableFlattening = null;
    public CheckBox checkBoxGCodeCenterZeroPoint = null;
    public ChoiceBox<GCodeBuilder.CommentType> choiceBoxCommentTypes = null;

    public TextArea textAreaGCodeStart = null;
    public TextArea textAreaGCodeEnd = null;
    public TextArea textAreaGCodePenDown = null;
    public TextArea textAreaGCodePenUp = null;
    public TextArea textAreaGCodeStartLayer = null;
    public TextArea textAreaGCodeEndLayer = null;

    public void initGCodeSettingsPane(){

        comboBoxGCodePreset.setItems(Register.PRESET_LOADER_GCODE_SETTINGS.presets);
        comboBoxGCodePreset.setValue(Register.PRESET_LOADER_GCODE_SETTINGS.getDefaultPreset());
        comboBoxGCodePreset.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                Register.PRESET_LOADER_GCODE_SETTINGS.applyPreset(newValue);
            }
        });

        FXHelper.setupPresetMenuButton(Register.PRESET_LOADER_GCODE_SETTINGS, menuButtonGCodePresets, false, comboBoxGCodePreset::getValue, (preset) -> {
            comboBoxGCodePreset.setValue(preset);

            ///force update rendering
            comboBoxGCodePreset.setItems(Register.PRESET_LOADER_GCODE_SETTINGS.presets);
            comboBoxGCodePreset.setButtonCell(new ComboBoxListCell<>());
        });

        textFieldOffsetX.textFormatterProperty().setValue(new TextFormatter<>(new FloatStringConverter(), 0F));
        textFieldOffsetX.textProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeOffsetX, new NumberStringConverter());

        textFieldOffsetY.textFormatterProperty().setValue(new TextFormatter<>(new FloatStringConverter(), 0F));
        textFieldOffsetY.textProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeOffsetY, new NumberStringConverter());

        choiceBoxGCodeUnits.getItems().addAll(UnitsLength.values());
        choiceBoxGCodeUnits.setValue(UnitsLength.MILLIMETRES);
        choiceBoxGCodeUnits.valueProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeUnits);

        textFieldGCodeCurveFlatness.textFormatterProperty().setValue(new TextFormatter<>(new FloatStringConverter(), 0.1F));
        textFieldGCodeCurveFlatness.textProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeCurveFlatness, new NumberStringConverter());
        textFieldGCodeCurveFlatness.disableProperty().bind(checkBoxGCodeEnableFlattening.selectedProperty().not());

        checkBoxGCodeEnableFlattening.setSelected(DrawingBotV3.INSTANCE.gcodeEnableFlattening.getValue());
        checkBoxGCodeEnableFlattening.selectedProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeEnableFlattening);

        checkBoxGCodeCenterZeroPoint.setSelected(DrawingBotV3.INSTANCE.gcodeCenterZeroPoint.getValue());
        checkBoxGCodeCenterZeroPoint.selectedProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeCenterZeroPoint);

        checkBoxGCodeCenterZeroPoint.setSelected(DrawingBotV3.INSTANCE.gcodeCenterZeroPoint.getValue());
        checkBoxGCodeCenterZeroPoint.selectedProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeCenterZeroPoint);


        choiceBoxCommentTypes.getItems().addAll(GCodeBuilder.CommentType.values());
        choiceBoxCommentTypes.setValue(GCodeBuilder.CommentType.BRACKETS);
        choiceBoxCommentTypes.valueProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeCommentType);

        textAreaGCodeStart.textProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeStartCode);
        textAreaGCodeStart.setText(GCodeExporter.defaultStartCode);

        textAreaGCodeEnd.textProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeEndCode);
        textAreaGCodeEnd.setText(GCodeExporter.defaultEndCode);

        textAreaGCodePenDown.textProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodePenDownCode);
        textAreaGCodePenDown.setText(GCodeExporter.defaultPenDownCode);

        textAreaGCodePenUp.textProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodePenUpCode);
        textAreaGCodePenUp.setText(GCodeExporter.defaultPenUpCode);

        textAreaGCodeStartLayer.textProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeStartLayerCode);
        textAreaGCodeStartLayer.setText(GCodeExporter.defaultStartLayerCode);

        textAreaGCodeEndLayer.textProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeEndLayerCode);
        textAreaGCodeEndLayer.setText(GCodeExporter.defaultEndLayerCode);

        /*

        choiceBoxGCodeXDir.valueProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeXDirection);
        choiceBoxGCodeXDir.setValue(EnumDirection.POSITIVE);
        choiceBoxGCodeXDir.setItems(FXCollections.observableArrayList(EnumDirection.values()));

        choiceBoxGCodeYDir.valueProperty().bindBidirectional(DrawingBotV3.INSTANCE.gcodeYDirection);
        choiceBoxGCodeYDir.setValue(EnumDirection.POSITIVE);
        choiceBoxGCodeYDir.setItems(FXCollections.observableArrayList(EnumDirection.values()));
         */

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ////IMAGE SEQUENCE SETTINGS
    public AnchorPane anchorPaneImgSeqSettings = null;

    public TextField textFieldDPI = null;
    public TextField textFieldFPS = null;
    public TextField textFieldDuration = null;
    public ChoiceBox<UnitsTime> choiceBoxTimeUnits = null;

    
    public Label labelExportSize = null;
    public Label labelFrameCount = null;
    public Label labelGeometriesPFrame = null;
    public Label labelVerticesPFrame = null;


    public void initImageSequencePane(){

        textFieldDPI.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), 300F));
        textFieldDPI.setText("" + ConfigFileHandler.getApplicationSettings().exportDPI);
        textFieldDPI.textProperty().addListener((observable, oldValue, newValue) -> {
            ConfigFileHandler.getApplicationSettings().exportDPI = Float.parseFloat(newValue);
            updateImageSequenceStats();
        });
        textFieldDPI.disableProperty().bind(DrawingBotV3.INSTANCE.controller.checkBoxOriginalSizing.selectedProperty());
        
        textFieldFPS.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), 25F));
        textFieldFPS.setText("" + ConfigFileHandler.getApplicationSettings().framesPerSecond);
        textFieldFPS.textProperty().addListener((observable, oldValue, newValue) -> {
            ConfigFileHandler.getApplicationSettings().framesPerSecond = Float.parseFloat(newValue);
            updateImageSequenceStats();
        });


        textFieldDuration.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 5));
        textFieldDuration.setText("" + ConfigFileHandler.getApplicationSettings().duration);
        textFieldDuration.textProperty().addListener((observable, oldValue, newValue) -> {
            ConfigFileHandler.getApplicationSettings().duration = Integer.parseInt(newValue);

            updateImageSequenceStats();
        });


        choiceBoxTimeUnits.setValue(ConfigFileHandler.getApplicationSettings().durationUnits);
        choiceBoxTimeUnits.setItems(FXCollections.observableArrayList(UnitsTime.values()));
        choiceBoxTimeUnits.valueProperty().addListener((observable, oldValue, newValue) -> {
            ConfigFileHandler.getApplicationSettings().durationUnits = newValue;
            updateImageSequenceStats();
        });

        updateImageSequenceStats();

    }

    public void updateImageSequenceStats(){
        int frameCount = ConfigFileHandler.getApplicationSettings().getFrameCount();
        int geometriesPerFrame = DrawingBotV3.INSTANCE.getActiveTask() == null ? 0 : ConfigFileHandler.getApplicationSettings().getGeometriesPerFrame(DrawingBotV3.INSTANCE.getActiveTask().plottedDrawing.getGeometryCount());
        long verticesPerFrame = DrawingBotV3.INSTANCE.getActiveTask() == null ? 0 : ConfigFileHandler.getApplicationSettings().getVerticesPerFrame(DrawingBotV3.INSTANCE.getActiveTask().plottedDrawing.getVertexCount());


        if(verticesPerFrame == 1 && frameCount > verticesPerFrame){
            frameCount = (int)(DrawingBotV3.INSTANCE.getActiveTask() == null ? 0 : DrawingBotV3.INSTANCE.getActiveTask().plottedDrawing.getVertexCount());
        }
        
        if(DrawingBotV3.INSTANCE.openImage.get() != null){
            if (!DrawingBotV3.INSTANCE.drawingArea.useOriginalSizing.get() && DrawingBotV3.INSTANCE.drawingArea.optimiseForPrint.get() && DrawingBotV3.INSTANCE.drawingArea.targetPenWidth.get() > 0){
                int DPI = (int)ConfigFileHandler.getApplicationSettings().exportDPI;
                int exportWidth = (int)Math.ceil((DrawingBotV3.INSTANCE.openImage.get().resolution.getPrintPageWidth() / UnitsLength.INCHES.convertToMM) * DPI);
                int exportHeight = (int)Math.ceil((DrawingBotV3.INSTANCE.openImage.get().resolution.getPrintPageHeight() / UnitsLength.INCHES.convertToMM) * DPI);            
                labelExportSize.setText(exportWidth + " x " + exportHeight);
            }else{
                int exportWidth = (int)DrawingBotV3.INSTANCE.openImage.get().resolution.getScaledWidth();
                int exportHeight = (int)DrawingBotV3.INSTANCE.openImage.get().resolution.getScaledHeight();
                labelExportSize.setText(exportWidth + " x " + exportHeight);
            }
        }else{
            labelExportSize.setText("0 x 0");
        }
        
        labelFrameCount.setText("" + Utils.defaultNF.format(frameCount));
        labelGeometriesPFrame.setText("" + Utils.defaultNF.format(geometriesPerFrame));
        labelVerticesPFrame.setText("" + Utils.defaultNF.format(verticesPerFrame));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ////HPGL SETTINGS
    public Tab tabHPGLSettings = null;
    public AnchorPane anchorPaneHPGLSettings = null;

    public ComboBox<String> comboBoxHPGLPresetCategory = null;
    public ComboBox<GenericPreset<?>> comboBoxHPGLPreset = null;
    public MenuButton menuButtonHPGLPresets = null;

    public TextField textFieldHPGLXMin = null;
    public TextField textFieldHPGLXMax = null;

    public TextField textFieldHPGLYMin = null;
    public TextField textFieldHPGLYMax = null;

    public CheckBox checkBoxHPGLMirrorX = null;
    public CheckBox checkBoxHPGLMirrorY = null;

    public ChoiceBox<EnumAlignment> choiceBoxHPGLAlignX = null;
    public ChoiceBox<EnumAlignment> choiceBoxHPGLAlignY = null;

    public ChoiceBox<EnumRotation> choiceBoxHPGLRotation = null;
    public TextField textFieldHPGLCurveFlatness = null;

    public TextField textFieldHPGLPenSpeed = null;
    public TextField textFieldHPGLPenNumber = null;


    public void initHPGLSettingsPane(){

    }

}
