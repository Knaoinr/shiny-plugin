# Shiny Plugin

A plugin for Shuffleboard that includes my homemade widgets, currently containing a widget for displaying the function of an Xbox controller and a widget displaying the time remaining in the match.

## Installation

Download the .jar file from [the latest release](https://github.com/Knaoinr/shiny-plugin/releases/) and drag it into the folder `~Shuffleboard/plugins`. This should work with any JDK version 11+.

## Usage

Drag and drop widgets to see what they look like, and right click > Edit Properties to see their properties.
All of these can be adjusted in the code by using the property string name. For example:

```java
operatorControllerWidget = buttonsTab.add("Operator Controller", null)
    .withWidget("Xbox Controller")
    .withProperties(Map.of("A descr", "this button does things", "button color", Color.GREY))
    .withPosition(0, 0)
    .withSize(4, 3).getEntry();
```

### Xbox Controller

![Xbox controller widget example](img/xbox.png)

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
operatorControllerWidget.setBooleanArray(array);
```

The button labels are set as properties as explained above.

### Match Time

![Timer widget example](img/timer.png)

The timer widget takes in a double as its data, the data type returned by `DriverStation.getInstance().getMatchTime()`. This method returns the time left in the current period, so be sure to add the length of teleop & endgame to this number if you're in auto. Also be aware that this method only returns an approximate time, not the official time.

The timer currently changes colors from purple (Match not started) -> blue (Autonomous) -> Green (Teleop) -> Orange (Endgame) -> Red (Match over).
You can disable the color changes as a setting. You can also change the lengths of each period as a setting. Currently, the lengths are set to the 2020 season (15 sec auto & 30 sec endgame over 2:30 min total).

Recommended code:

```java
timerWidget.setDouble(DriverStation.getInstance().getMatchTime() + (DriverStation.getInstance().isAutonomous() ? 135 : 0));
```
