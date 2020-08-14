package com.knaoinr.customwidgets.widget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;

import com.google.common.collect.ImmutableList;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A widget that looks like an Xbox controller, with customizable properties such as color and descriptive Strings for each button.
 */
@Description(
    name = "Xbox Controller",
    dataTypes = { boolean[].class }, //up to 13 booleans, one for each button
    summary = "Displays the functions of a controller and its various fun things."
)
@ParametrizedController("XboxWidget.fxml")
public final class XboxWidget extends SimpleAnnotatedWidget<boolean[]> {

    private final Property<Color> colorWhenPressed
        = new SimpleObjectProperty<>(this, "colorWhenPressed", Color.LAWNGREEN);
    private final Property<Color> backgroundColor
        = new SimpleObjectProperty<>(this, "backgroundColor", Color.GREY);
    private final Property<Color> buttonColor
        = new SimpleObjectProperty<>(this, "buttonColor", Color.DARKGREY);
    private final Property<Color> textColor1
        = new SimpleObjectProperty<>(this, "textColor1", Color.BLACK);
    private final Property<Color> textColor2
        = new SimpleObjectProperty<>(this, "textColor2", Color.BLACK);

    private final Property<String> aDesc
        = new SimpleObjectProperty<>(this, "aDesc", "");
    private final Property<String> bDesc
        = new SimpleObjectProperty<>(this, "bDesc", "");
    private final Property<String> xDesc
        = new SimpleObjectProperty<>(this, "xDesc", "");
    private final Property<String> yDesc
        = new SimpleObjectProperty<>(this, "yDesc", "");
    private final Property<String> lbDesc
        = new SimpleObjectProperty<>(this, "lbDesc", "");
    private final Property<String> rbDesc
        = new SimpleObjectProperty<>(this, "rbDesc", "");
    private final Property<String> lsDesc
        = new SimpleObjectProperty<>(this, "lsDesc", "");
    private final Property<String> rsDesc
        = new SimpleObjectProperty<>(this, "rsDesc", "");
    private final Property<String> leftJoystickDesc
        = new SimpleObjectProperty<>(this, "leftJoystickDesc", "");
    private final Property<String> rightJoystickDesc
        = new SimpleObjectProperty<>(this, "rightJoystickDesc", "");
    private final Property<String> leftTriggerDesc
        = new SimpleObjectProperty<>(this, "leftTriggerDesc", "");
    private final Property<String> rightTriggerDesc
        = new SimpleObjectProperty<>(this, "rightTriggerDesc", "");
    private final Property<String> POVDesc
        = new SimpleObjectProperty<>(this, "POVDesc", "");

    @FXML
    private Pane root;

    @FXML
    private Label aDescView;
    @FXML
    private Label bDescView;
    @FXML
    private Label xDescView;
    @FXML
    private Label yDescView;
    @FXML
    private Label lbDescView;
    @FXML
    private Label rbDescView;
    @FXML
    private Label lsDescView;
    @FXML
    private Label rsDescView;
    @FXML
    private Label leftJoystickDescView;
    @FXML
    private Label rightJoystickDescView;
    @FXML
    private Label leftTriggerDescView;
    @FXML
    private Label rightTriggerDescView;
    @FXML
    private Label POVDescView;

    @FXML
    private Circle a;
    @FXML
    private Circle b;
    @FXML
    private Circle x;
    @FXML
    private Circle y;
    @FXML
    private Rectangle lb;
    @FXML
    private Rectangle rb;
    @FXML
    private Circle ls;
    @FXML
    private Circle rs;
    @FXML
    private Circle leftJoystick;
    @FXML
    private Circle rightJoystick;
    @FXML
    private Rectangle leftTrigger;
    @FXML
    private Rectangle rightTrigger;
    @FXML
    private Rectangle horizontalPOV;
    @FXML
    private Rectangle verticalPOV;

    @FXML
    private void initialize() {
        SVGPath svg = new SVGPath();
        svg.setContent("M 375 350 Q 400 335 425 350 C 463 369 463 425 500 450 Q 550 488 600 450 Q 649 396 650 300 Q 649 179 600 150 Q "
        + "550 124 500 150 L 451 180 Q 401 198 351 180 L 300 150 Q 250 124 200 150 Q 150 179 150 300 Q 150 395 200 450 Q 250 488 300 450 "
        + "C 341 425 337 368 375 350 ");
        root.setShape(svg);
        root.backgroundProperty().bind(Bindings.createObjectBinding(() -> getSolidBackground(backgroundColor.getValue()), backgroundColor));

        a.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[0] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        a.radiusProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.05*root.getHeight(), 0.03*root.getWidth()), root.heightProperty(), root.widthProperty()));
        a.centerXProperty().bind(Bindings.createDoubleBinding(() -> 0.85*root.getWidth(), root.widthProperty()));
        a.centerYProperty().bind(Bindings.createDoubleBinding(() -> 0.35*root.getHeight() + 0.8*leftJoystick.getRadius(), root.heightProperty(), leftJoystick.radiusProperty()));

        b.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[1] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        b.radiusProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.05*root.getHeight(), 0.03*root.getWidth()), root.heightProperty(), root.widthProperty()));
        b.centerXProperty().bind(Bindings.createDoubleBinding(() -> 0.85*root.getWidth() + 0.8*leftJoystick.getRadius(), root.widthProperty(), leftJoystick.radiusProperty()));
        b.centerYProperty().bind(Bindings.createDoubleBinding(() -> 0.35*root.getHeight(), root.heightProperty()));

        x.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[2] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        x.radiusProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.05*root.getHeight(), 0.03*root.getWidth()), root.heightProperty(), root.widthProperty()));
        x.centerXProperty().bind(Bindings.createDoubleBinding(() -> 0.85*root.getWidth() - 0.8*leftJoystick.getRadius(), root.widthProperty(), leftJoystick.radiusProperty()));
        x.centerYProperty().bind(Bindings.createDoubleBinding(() -> 0.35*root.getHeight(), root.heightProperty()));

        y.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[3] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        y.radiusProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.05*root.getHeight(), 0.03*root.getWidth()), root.heightProperty(), root.widthProperty()));
        y.centerXProperty().bind(Bindings.createDoubleBinding(() -> 0.85*root.getWidth(), root.widthProperty(), leftJoystick.radiusProperty()));
        y.centerYProperty().bind(Bindings.createDoubleBinding(() -> 0.35*root.getHeight() - 0.8*leftJoystick.getRadius(), root.heightProperty(), leftJoystick.radiusProperty()));

        lb.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[4] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        lb.widthProperty().bind(Bindings.createDoubleBinding(() -> 0.133*root.getWidth(), root.widthProperty()));
        lb.heightProperty().bind(Bindings.createDoubleBinding(() ->0.036*root.getHeight(), root.heightProperty()));
        lb.xProperty().bind(Bindings.createDoubleBinding(() -> 0.195*root.getWidth() - lb.getWidth()/2.0, root.widthProperty(), lb.widthProperty()));
        lb.yProperty().bind(Bindings.createDoubleBinding(() -> 0.11*root.getHeight() - lb.getHeight()/2.0, root.heightProperty(), lb.heightProperty()));

        rb.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[5] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        rb.widthProperty().bind(Bindings.createDoubleBinding(() -> 0.133*root.getWidth(), root.widthProperty()));
        rb.heightProperty().bind(Bindings.createDoubleBinding(() ->0.036*root.getHeight(), root.heightProperty()));
        rb.xProperty().bind(Bindings.createDoubleBinding(() -> 0.805*root.getWidth() - rb.getWidth()/2.0, root.widthProperty(), rb.widthProperty()));
        rb.yProperty().bind(Bindings.createDoubleBinding(() -> 0.11*root.getHeight() - rb.getHeight()/2.0, root.heightProperty(), rb.heightProperty()));

        ls.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[6] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        ls.radiusProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.025*root.getHeight(), 0.015*root.getWidth()), root.heightProperty(), root.widthProperty()));
        ls.centerXProperty().bind(Bindings.createDoubleBinding(() -> 0.43*root.getWidth(), root.widthProperty()));
        ls.centerYProperty().bind(Bindings.createDoubleBinding(() -> 0.29*root.getHeight(), root.heightProperty()));

        rs.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[7] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        rs.radiusProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.025*root.getHeight(), 0.015*root.getWidth()), root.heightProperty(), root.widthProperty()));
        rs.centerXProperty().bind(Bindings.createDoubleBinding(() -> 0.57*root.getWidth(), root.widthProperty()));
        rs.centerYProperty().bind(Bindings.createDoubleBinding(() -> 0.29*root.getHeight(), root.heightProperty()));

        leftJoystick.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[8] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        leftJoystick.radiusProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.125*root.getHeight(), 0.085*root.getWidth()), root.heightProperty(), root.widthProperty()));
        leftJoystick.centerXProperty().bind(Bindings.createDoubleBinding(() -> 0.15*root.getWidth(), root.widthProperty()));
        leftJoystick.centerYProperty().bind(Bindings.createDoubleBinding(() -> 0.35*root.getHeight(), root.heightProperty()));

        rightJoystick.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[9] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        rightJoystick.radiusProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.125*root.getHeight(), 0.085*root.getWidth()), root.heightProperty(), root.widthProperty()));
        rightJoystick.centerXProperty().bind(Bindings.createDoubleBinding(() -> 0.685*root.getWidth(), root.widthProperty()));
        rightJoystick.centerYProperty().bind(Bindings.createDoubleBinding(() -> 0.57*root.getHeight(), root.heightProperty()));

        leftTrigger.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[10] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        leftTrigger.widthProperty().bind(Bindings.createDoubleBinding(() -> 0.133*root.getWidth(), root.widthProperty()));
        leftTrigger.heightProperty().bind(Bindings.createDoubleBinding(() ->0.07*root.getHeight(), root.heightProperty()));
        leftTrigger.xProperty().bind(Bindings.createDoubleBinding(() -> 0.195*root.getWidth() - leftTrigger.getWidth()/2.0, root.widthProperty(), leftTrigger.widthProperty()));
        leftTrigger.yProperty().bind(Bindings.createDoubleBinding(() -> 0.024*root.getHeight() - leftTrigger.getHeight()/2.0, root.heightProperty(), leftTrigger.heightProperty()));

        rightTrigger.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[11] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        rightTrigger.widthProperty().bind(Bindings.createDoubleBinding(() -> 0.133*root.getWidth(), root.widthProperty()));
        rightTrigger.heightProperty().bind(Bindings.createDoubleBinding(() ->0.07*root.getHeight(), root.heightProperty()));
        rightTrigger.xProperty().bind(Bindings.createDoubleBinding(() -> 0.805*root.getWidth() - rightTrigger.getWidth()/2.0, root.widthProperty(), rightTrigger.widthProperty()));
        rightTrigger.yProperty().bind(Bindings.createDoubleBinding(() -> 0.024*root.getHeight() - rightTrigger.getHeight()/2.0, root.heightProperty(), rightTrigger.heightProperty()));

        horizontalPOV.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[12] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        horizontalPOV.widthProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.25*root.getHeight(), 0.17*root.getWidth()), root.heightProperty(), root.widthProperty()));
        horizontalPOV.heightProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.25*root.getHeight()/3.0, 0.17*root.getWidth()/3.0), root.heightProperty(), root.widthProperty()));
        horizontalPOV.xProperty().bind(Bindings.createDoubleBinding(() -> 0.315*root.getWidth() - horizontalPOV.getWidth()/2.0, root.widthProperty(), horizontalPOV.widthProperty()));
        horizontalPOV.yProperty().bind(Bindings.createDoubleBinding(() -> 0.57*root.getHeight() - horizontalPOV.getHeight()/2.0, root.heightProperty(), horizontalPOV.heightProperty()));

        verticalPOV.fillProperty().bind(Bindings.createObjectBinding(() -> getData()[12] ? colorWhenPressed.getValue() : buttonColor.getValue(), dataProperty(), colorWhenPressed, buttonColor));
        verticalPOV.widthProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.25*root.getHeight()/3.0, 0.17*root.getWidth()/3.0), root.heightProperty(), root.widthProperty()));
        verticalPOV.heightProperty().bind(Bindings.createDoubleBinding(() -> Math.min(0.25*root.getHeight(), 0.17*root.getWidth()), root.heightProperty(), root.widthProperty()));
        verticalPOV.xProperty().bind(Bindings.createDoubleBinding(() -> 0.315*root.getWidth() - verticalPOV.getWidth()/2.0, root.widthProperty(), verticalPOV.widthProperty()));
        verticalPOV.yProperty().bind(Bindings.createDoubleBinding(() -> 0.57*root.getHeight() - verticalPOV.getHeight()/2.0, root.heightProperty(), verticalPOV.heightProperty()));

        aDescView.textProperty().bind(aDesc);
        bDescView.textProperty().bind(bDesc);
        xDescView.textProperty().bind(xDesc);
        yDescView.textProperty().bind(yDesc);
        lbDescView.textProperty().bind(lbDesc);
        rbDescView.textProperty().bind(rbDesc);
        lsDescView.textProperty().bind(lsDesc);
        rsDescView.textProperty().bind(rsDesc);
        leftJoystickDescView.textProperty().bind(leftJoystickDesc);
        rightJoystickDescView.textProperty().bind(rightJoystickDesc);
        leftTriggerDescView.textProperty().bind(leftTriggerDesc);
        rightTriggerDescView.textProperty().bind(rightTriggerDesc);
        POVDescView.textProperty().bind(POVDesc);

        aDescView.textFillProperty().bind(textColor1);
        bDescView.textFillProperty().bind(textColor1);
        xDescView.textFillProperty().bind(textColor1);
        yDescView.textFillProperty().bind(textColor1);
        lbDescView.textFillProperty().bind(textColor1);
        rbDescView.textFillProperty().bind(textColor1);
        lsDescView.textFillProperty().bind(textColor1);
        rsDescView.textFillProperty().bind(textColor2);
        leftJoystickDescView.textFillProperty().bind(textColor1);
        rightJoystickDescView.textFillProperty().bind(textColor1);
        leftTriggerDescView.textFillProperty().bind(textColor2);
        rightTriggerDescView.textFillProperty().bind(textColor2);
        POVDescView.textFillProperty().bind(textColor1);

        aDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.2*root.getWidth(), root.widthProperty()));
        aDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 0.25*root.getWidth(), root.widthProperty()));
        aDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> 0.8*root.getWidth(), root.widthProperty()));
        aDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> a.getCenterY() - a.getRadius()/2.0, a.centerYProperty(), a.radiusProperty()));

        bDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.4*root.getWidth(), root.widthProperty()));
        bDescView.maxHeightProperty().bind(b.radiusProperty());
        bDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> 0.59*root.getWidth(), root.widthProperty()));
        bDescView.translateYProperty().bind(b.centerYProperty());

        xDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.3*root.getWidth(), root.widthProperty()));
        xDescView.maxHeightProperty().bind(x.radiusProperty());
        xDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> 0.59*root.getWidth(), root.widthProperty()));
        xDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> x.getCenterY() - 1.1*x.getRadius(), x.centerYProperty(), x.radiusProperty()));

        yDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.37*root.getWidth(), root.widthProperty()));
        yDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 2.5*y.getRadius(), y.radiusProperty()));
        yDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> 0.62*root.getWidth(), root.widthProperty()));
        yDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> y.getCenterY() - 1.25*y.getRadius(), y.centerYProperty(), y.radiusProperty()));

        lbDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.31*root.getWidth(), root.widthProperty()));
        lbDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 5*lb.getHeight(), lb.heightProperty()));
        lbDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> 0.04*root.getWidth(), root.widthProperty()));
        lbDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> lb.getY() - 5, lb.yProperty()));

        rbDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.31*root.getWidth(), root.widthProperty()));
        rbDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 5*rb.getHeight(), rb.heightProperty()));
        rbDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> 0.65*root.getWidth(), root.widthProperty()));
        rbDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> rb.getY() - 5, rb.yProperty()));

        lsDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.31*root.getWidth(), root.widthProperty()));
        lsDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 0.19*root.getHeight(), root.heightProperty()));
        lsDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> 0.29*root.getWidth(), root.widthProperty()));
        lsDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> 0.31*root.getHeight(), root.heightProperty()));

        rsDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.2*root.getWidth(), root.widthProperty()));
        rsDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 0.3*root.getHeight(), root.heightProperty()));
        rsDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> 0.42*root.getWidth(), root.widthProperty()));
        rsDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> -0.03*root.getHeight(), root.heightProperty()));

        leftJoystickDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 2.5*leftJoystick.getRadius(), leftJoystick.radiusProperty()));
        leftJoystickDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 2.5*leftJoystick.getRadius(), leftJoystick.radiusProperty()));
        leftJoystickDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> leftJoystick.getCenterX() - 1.25*leftJoystick.getRadius(), leftJoystick.centerXProperty(), leftJoystick.radiusProperty()));
        leftJoystickDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> leftJoystick.getCenterY() - 1.25*leftJoystick.getRadius(), leftJoystick.centerYProperty(), leftJoystick.radiusProperty()));

        rightJoystickDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 2.5*rightJoystick.getRadius(), rightJoystick.radiusProperty()));
        rightJoystickDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 2.5*rightJoystick.getRadius(), rightJoystick.radiusProperty()));
        rightJoystickDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> rightJoystick.getCenterX() - 1.25*rightJoystick.getRadius(), rightJoystick.centerXProperty(), rightJoystick.radiusProperty()));
        rightJoystickDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> rightJoystick.getCenterY() - 1.25*rightJoystick.getRadius(), rightJoystick.centerYProperty(), rightJoystick.radiusProperty()));

        leftTriggerDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.39*root.getWidth(), root.widthProperty()));
        leftTriggerDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 0.1*root.getHeight(), root.heightProperty()));
        leftTriggerDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> -0.03*root.getHeight(), root.heightProperty()));

        rightTriggerDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 0.39*root.getWidth(), root.widthProperty()));
        rightTriggerDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 0.1*root.getHeight(), root.heightProperty()));
        rightTriggerDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> 0.61*root.getWidth(), root.widthProperty()));
        rightTriggerDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> -0.03*root.getHeight(), root.heightProperty()));

        POVDescView.maxWidthProperty().bind(Bindings.createDoubleBinding(() -> 1.25*horizontalPOV.getWidth(), horizontalPOV.widthProperty()));
        POVDescView.maxHeightProperty().bind(Bindings.createDoubleBinding(() -> 1.25*verticalPOV.getHeight(), verticalPOV.heightProperty()));
        POVDescView.translateXProperty().bind(Bindings.createDoubleBinding(() -> horizontalPOV.getX() - 0.125*horizontalPOV.getWidth(), horizontalPOV.xProperty(), horizontalPOV.widthProperty()));
        POVDescView.translateYProperty().bind(Bindings.createDoubleBinding(() -> verticalPOV.getY() - 0.125*verticalPOV.getHeight(), verticalPOV.heightProperty()));

        aDescView.minWidthProperty().bind(aDescView.maxWidthProperty());
        bDescView.minWidthProperty().bind(bDescView.maxWidthProperty());
        xDescView.minWidthProperty().bind(xDescView.maxWidthProperty());
        yDescView.minWidthProperty().bind(yDescView.maxWidthProperty());
        lbDescView.minWidthProperty().bind(lbDescView.maxWidthProperty());
        rbDescView.minWidthProperty().bind(rbDescView.maxWidthProperty());
        lsDescView.minWidthProperty().bind(lsDescView.maxWidthProperty());
        rsDescView.minWidthProperty().bind(rsDescView.maxWidthProperty());
        leftJoystickDescView.minWidthProperty().bind(leftJoystickDescView.maxWidthProperty());
        rightJoystickDescView.minWidthProperty().bind(rightJoystickDescView.maxWidthProperty());
        leftTriggerDescView.minWidthProperty().bind(leftTriggerDescView.maxWidthProperty());
        rightTriggerDescView.minWidthProperty().bind(rightTriggerDescView.maxWidthProperty());
        POVDescView.minWidthProperty().bind(POVDescView.maxWidthProperty());

        aDescView.minHeightProperty().bind(aDescView.maxHeightProperty());
        bDescView.minHeightProperty().bind(bDescView.maxHeightProperty());
        xDescView.minHeightProperty().bind(xDescView.maxHeightProperty());
        yDescView.minHeightProperty().bind(yDescView.maxHeightProperty());
        lbDescView.minHeightProperty().bind(lbDescView.maxHeightProperty());
        rbDescView.minHeightProperty().bind(rbDescView.maxHeightProperty());
        lsDescView.minHeightProperty().bind(lsDescView.maxHeightProperty());
        rsDescView.minHeightProperty().bind(rsDescView.maxHeightProperty());
        leftJoystickDescView.minHeightProperty().bind(leftJoystickDescView.maxHeightProperty());
        rightJoystickDescView.minHeightProperty().bind(rightJoystickDescView.maxHeightProperty());
        leftTriggerDescView.minHeightProperty().bind(leftTriggerDescView.maxHeightProperty());
        rightTriggerDescView.minHeightProperty().bind(rightTriggerDescView.maxHeightProperty());
        POVDescView.minHeightProperty().bind(POVDescView.maxHeightProperty());
    }

    @Override
    public Pane getView() {
        return root;
    }

    @Override
    public List<Group> getSettings() {
        return ImmutableList.of(
            Group.of("Colors",
                Setting.of("Color when pressed", "The color to use when a button is pressed", colorWhenPressed, Color.class),
                Setting.of("Background color", "The color to use as the background of the controller", backgroundColor, Color.class),
                Setting.of("Button color", "The color to use for the buttons of the controller", buttonColor, Color.class),
                Setting.of("Text color 1", "The text color to use on the body of the controller", textColor1, Color.class),
                Setting.of("Text color 2", "The text color to use off the body of the controller", textColor2, Color.class)
            ),
            Group.of("Descriptions",
                Setting.of("A descr", aDesc, String.class),
                Setting.of("B descr", bDesc, String.class),
                Setting.of("X descr", xDesc, String.class),
                Setting.of("Y descr", yDesc, String.class),
                Setting.of("LB descr", lbDesc, String.class),
                Setting.of("RB descr", rbDesc, String.class),
                Setting.of("LS descr", lsDesc, String.class),
                Setting.of("RS descr", rsDesc, String.class),
                Setting.of("Left joystick descr", leftJoystickDesc, String.class),
                Setting.of("Right joystick descr", rightJoystickDesc, String.class),
                Setting.of("Left trigger descr", leftTriggerDesc, String.class),
                Setting.of("Right trigger descr", rightTriggerDesc, String.class),
                Setting.of("POV descr", POVDesc, String.class)
            )
        );
    }

    private Background getSolidBackground(Color color) {
        return new Background(new BackgroundFill(color, null, null));
    }

    @Override
    public boolean[] getData() {
        boolean[] data = super.getData();
        boolean[] array = new boolean[13];
        //in case data is null or incomplete ->
        if (data == null || data.length == 0) {
            return array;
        }
        for(int i = 0; i < 13; i++) {
            if (data.length > i) {
                array[i] = data[i];
            } else {
                array[i] = false;
            }
        }
        return array;
    }
}