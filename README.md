# Shiny Plugin

A plugin for Shuffleboard that includes my homemade widgets, currently limited to a widget for displaying the function of an Xbox controller.

## Installation

Download the .jar file from [the latest release](https://github.com/Knaoinr/shiny-plugin/releases/) and drag it into the folder `~Shuffleboard/plugins`.

## Usage

Drag and drop widgets to see what they look like, and right click > Edit Properties to see their properties.
All of these can be adjusted in the code by using the property string name. For example:

```java
operatorControllerWidget = buttonsTab.add("Operator Controller", null)
    .withWidget("Xbox Controller")
    .withProperties(Map.of("A descr", "this button does things, "button color", Color.GREY))
    .withPosition(0, 0)
    .withSize(4, 3).getEntry();
```

The Xbox controller widget takes in a boolean array as its data, up to 13 booleans, each representing whether the button is pressed:
* 0: A
* 1: B
* 2: X
* 3: Y
* 4: LB
* 5: RB
* 6: LS
* 7: LR
* 8: Left joystick
* 9: Right joystick
* 10: Left trigger
* 11: Right trigger
* 12: POV

The default is false for all of these.

To set this in the code:

```java
operatorControllerWidget.setObject(array);
```
