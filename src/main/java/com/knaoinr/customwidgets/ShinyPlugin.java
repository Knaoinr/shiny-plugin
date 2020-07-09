package com.knaoinr.customwidgets;

import edu.wpi.first.shuffleboard.api.data.DataType;
import edu.wpi.first.shuffleboard.api.data.types.BooleanArrayType;
import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

import com.google.common.collect.ImmutableList;
import com.knaoinr.customwidgets.widget.XboxWidget;

import java.util.List;
import java.util.Map;

/**
 * A plugin for displaying the functions of an Xbox controller.
 */
@Description(
    group = "com.knaoinr",
    name = "Shiny Plugin",
    version = "1.0.1",
    summary = "A plugin for displaying custom widgets, such as an Xbox controller."
)
public final class ShinyPlugin extends Plugin {

    @Override
    public List<ComponentType> getComponents() {
        return ImmutableList.of(
            WidgetType.forAnnotatedWidget(XboxWidget.class)
        );
    }

    @Override
    public Map<DataType, ComponentType> getDefaultComponents() {
        return Map.of(
            BooleanArrayType.Instance, WidgetType.forAnnotatedWidget(XboxWidget.class)
        );
    }
}