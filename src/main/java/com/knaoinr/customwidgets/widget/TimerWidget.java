package com.knaoinr.customwidgets.widget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * A widget that displays the amount of time left in the match.
 */
@Description(
    name = "Match Time",
    dataTypes = { Double.class },
    summary = "Displays the time remaining in a match. Match length is configured for the 2020 season."
)
@ParametrizedController("TimerWidget.fxml")
public final class TimerWidget extends SimpleAnnotatedWidget<Double> {

    private final Property<Boolean> colorsOn
        = new SimpleBooleanProperty(this, "Colors on", true);

    private final Property<Number> autonomous
        = new SimpleDoubleProperty(this, "Autonomous length", 15);
    private final Property<Number> teleop
        = new SimpleDoubleProperty(this, "Teleop length", 105); //this does NOT include endgame
    private final Property<Number> endgame
        = new SimpleDoubleProperty(this, "Endgame length", 30);

    @FXML
    private Pane root;
    @FXML
    private Label timerLabel;

    @FXML
    private void initialize() {
        timerLabel.textProperty().bind(dataOrDefault.map(seconds -> (int) Math.ceil(seconds)/60 + ":" + (Math.ceil(seconds)%60 < 10 ? "0" : "") + (int) Math.ceil(seconds)%60));
        timerLabel.minWidthProperty().bind(root.widthProperty());
        timerLabel.minHeightProperty().bind(root.heightProperty());

        timerLabel.textFillProperty().bind(Bindings.createObjectBinding(() -> getColor(dataOrDefault.getValue()), dataProperty(), colorsOn, autonomous, teleop, endgame));

        timerLabel.setFont(new Font(30));
    }

    @Override
    public Pane getView() {
        return root;
    }

    private Color getColor(double time) {
        if (!colorsOn.getValue()) {
            return Color.BLACK;
        }
        else {
            if (time <= 0) {
                return Color.RED;
            }
            else if (time < (double) endgame.getValue()) {
                return Color.ORANGE;
            }
            else if (time < (double) endgame.getValue() + (double) teleop.getValue()) {
                return Color.GREEN;
            }
            else if (time < (double) endgame.getValue() + (double) teleop.getValue() + (double) autonomous.getValue()) {
                return Color.BLUE;
            }
            else {
                return Color.PURPLE;
            }
        }
    }
}