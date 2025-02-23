package drawingbot.javafx.controls;

import drawingbot.DrawingBotV3;
import drawingbot.drawing.DrawingPen;
import drawingbot.image.ImageTools;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class ComboCellDrawingPen extends ComboBoxListCell<DrawingPen> {

    public final HBox hbox;
    public final CheckBox checkBox;
    public final Rectangle colour;
    public Callback<DrawingPen, BooleanProperty> propertyCallback;

    private BooleanProperty property;

    public ComboCellDrawingPen(boolean useCheckBox) {
        super();
        hbox = new HBox();

        if (useCheckBox) {
            propertyCallback = param -> {
                SimpleBooleanProperty prop = new SimpleBooleanProperty(DrawingBotV3.INSTANCE.activeDrawingSet.get().containsPen(param));
                prop.addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        DrawingBotV3.INSTANCE.activeDrawingSet.get().addNewPen(getItem());
                    } else {
                        DrawingBotV3.INSTANCE.activeDrawingSet.get().pens.removeIf((p) -> p.getCodeName().equals(getItem().getCodeName()));
                    }
                });
                return prop;
            };
            checkBox = new CheckBox();
            hbox.setSpacing(10);
            hbox.getChildren().add(checkBox);
            hbox.setAlignment(Pos.CENTER_LEFT);

            setOnMouseClicked(e -> DrawingBotV3.INSTANCE.controller.comboBoxDrawingPen.hide());

        } else {
            checkBox = null;
        }
        colour = new Rectangle(20, 12, Color.AQUA);
        hbox.getChildren().add(colour);
    }

    @Override
    public void updateItem(DrawingPen item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText("  " + item.toString());
            setGraphic(hbox);
            if (checkBox != null) {
                if (property != null) {
                    checkBox.selectedProperty().unbindBidirectional(property);
                }
                property = propertyCallback.call(item);
                if (property != null) {
                    checkBox.selectedProperty().bindBidirectional(property);
                }
            }
            colour.setFill(ImageTools.getColorFromARGB(item.getARGB()));
        }
    }
}
