package com.knaoinr.customwidgets;

import edu.wpi.first.shuffleboard.api.data.DataType;
import edu.wpi.first.shuffleboard.api.data.types.BooleanArrayType;
import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

import com.google.common.collect.ImmutableList;
import com.knaoinr.customwidgets.widget.*;

import java.util.List;
import java.util.Map;

/**
 * A plugin for displaying custom widgets.
 */
@Description(
    group = "com.knaoinr",
    name = "Shiny Plugin",
    version = "1.2.0",
    summary = "A plugin for displaying custom widgets, such as an Xbox controller, the match time, or the robot's path."
)
public final class ShinyPlugin extends Plugin {

    @Override
    public List<ComponentType> getComponents() {
        return ImmutableList.of(
            WidgetType.forAnnotatedWidget(XboxWidget.class),
            WidgetType.forAnnotatedWidget(TimerWidget.class),
            WidgetType.forAnnotatedWidget(PathWidget.class)
        );
    }

    @Override
    public Map<DataType, ComponentType> getDefaultComponents() {
        return Map.of(
            BooleanArrayType.Instance, WidgetType.forAnnotatedWidget(XboxWidget.class)
        );
    }
}