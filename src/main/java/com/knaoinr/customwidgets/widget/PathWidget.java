package com.knaoinr.customwidgets.widget;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

/**
 * A widget that displays the amount of time left in the match.
 */
@Description(name = "Path",
             dataTypes = { double[].class },
             summary = "Draws the path of a given trajectory."
)
@ParametrizedController("PathWidget.fxml")
public final class PathWidget extends SimpleAnnotatedWidget<double[]> {

    private final Property<Number> year = new SimpleIntegerProperty(this, "year", 2020);
    private final Property<Number> fieldWidth = new SimpleDoubleProperty(this, "field width", 26*12 + 11.25);
    private final Property<Number> fieldHeight = new SimpleDoubleProperty(this, "field height", 52*12 + 5.25);

    @FXML
    private Pane root;
    @FXML
    private ImageView imageView;
    @FXML
    private SVGPath path;

    @FXML
    private void initialize() {
        imageView.imageProperty().bind(Bindings.createObjectBinding(() -> new Image(getClass().getResourceAsStream("path/" + year.getValue() + ".png")), year));
        imageView.preserveRatioProperty().set(true);
        imageView.fitHeightProperty().bind(root.heightProperty());
        imageView.fitWidthProperty().bind(root.widthProperty());
        imageView.xProperty().bind(Bindings.createDoubleBinding(() -> (imageView.getFitWidth() - getImageWidth()) / 2, root.widthProperty(), root.heightProperty(), year));
        imageView.yProperty().bind(Bindings.createDoubleBinding(() -> (imageView.getFitHeight() - getImageHeight()) / 2, root.widthProperty(), root.heightProperty(), year));

        path.contentProperty().bind(Bindings.createObjectBinding(() -> getPathContent(), dataProperty()));
        path.setStroke(Color.RED);
        path.setStrokeWidth(5);
        path.setFill(null);

        path.scaleXProperty().bind(Bindings.createDoubleBinding(() -> getImageWidth()/(double)fieldWidth.getValue(), root.widthProperty(), root.heightProperty(), year, fieldWidth));
        path.scaleYProperty().bind(Bindings.createDoubleBinding(() -> getImageHeight()/(double)fieldHeight.getValue(), root.widthProperty(), root.heightProperty(), year, fieldHeight));
        path.translateXProperty().bind(Bindings.createDoubleBinding(() -> (root.getWidth() - (double)fieldWidth.getValue())/2, path.scaleXProperty()));
        path.translateYProperty().bind(Bindings.createDoubleBinding(() -> (root.getHeight() - (double)fieldHeight.getValue())/2, path.scaleYProperty()));
    }

    @Override
    public Pane getView() {
        return root;
    }

    @Override
    public List<Group> getSettings() {
        return ImmutableList.of(
            Group.of("Year",
                Setting.of("Year", year, Integer.class),
                Setting.of("Field width", fieldWidth, Double.class),
                Setting.of("Field height", fieldHeight, Double.class)
            )
        );
    }

    private double getImageWidth() {
        double ratioX = root.getWidth() / imageView.getImage().getWidth();
        double ratioY = root.getHeight() / imageView.getImage().getHeight();

        if (ratioX <= ratioY) return root.getWidth();
        else return imageView.getImage().getWidth() * ratioY;
    }

    private double getImageHeight() {
        double ratioX = root.getWidth() / imageView.getImage().getWidth();
        double ratioY = root.getHeight() / imageView.getImage().getHeight();

        if (ratioY <= ratioX) return root.getHeight();
        else return imageView.getImage().getHeight() * ratioX;
    }

    private String getPathContent() {
        Logger logger = LogManager.getLogManager().getLogger("");
        StringBuffer content = new StringBuffer();
        double[] property = dataProperty().get();
        if (property.length%2 != 0) logger.log(Level.FINE, "Path data input does not have an even number of entries. The end entry will be disregarded.");

        if (property.length > 1) {
            content.append("M" + property[0] + " " + property[1]);
        }
        if (property.length > 3) {
            content.append('L');
            for(int i = 2; i < property.length-1; i+=2) {
                if (property[i] > (double) fieldWidth.getValue() || property[i] < 0) {
                    logger.log(Level.FINE, "Path data entry " + i + " (" + property[i] + ") is not between 0 and " + fieldWidth.getValue() + ". This pair will be skipped.");
                    continue;
                } else if (property[i+1] > (double) fieldHeight.getValue() || property[i+1] < 0) {
                    logger.log(Level.FINE, "Path data entry " + i+1 + " (" + property[i+1] + ") is not between 0 and " + fieldHeight.getValue() + ". This pair will be skipped.");
                    continue;
                }
                content.append(property[i] + " " + property[i+1] + " ");
            }
        }
        if (content.charAt(content.length()-1) == 'L') content.deleteCharAt(content.length()-1); //in case all were skipped

        content.append("M 0 0l0 0.00001M" + fieldWidth.getValue() + " " + fieldHeight.getValue() + "l-0.00001 0"); //dot corners for scaling reasons

        return content.toString();
    }
}