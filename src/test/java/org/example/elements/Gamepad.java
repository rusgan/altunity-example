package org.example.elements;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import ro.altom.altunitytester.AltUnityObject;

import static org.example.DriverSettings.appiumDriver;
import static org.example.elements.Menu.waitForElement;

public class Gamepad {

    public static AltUnityObject getUp(){

        return waitForElement("Up");
    }

    public static AltUnityObject getLeft(){

        return waitForElement("Left");
    }

    public static AltUnityObject getRight(){

        return waitForElement("Right");
    }

    public static AltUnityObject getInteract(){

        return waitForElement("Interact");
    }

    public static void tap(AltUnityObject object){

        TouchAction touchAction = new TouchAction(appiumDriver);
        int y = appiumDriver.manage().window().getSize().height;
        touchAction.tap(new PointOption().withCoordinates(object.getX(), y - object.getY())).perform();
    }
}
