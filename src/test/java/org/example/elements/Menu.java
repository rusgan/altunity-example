package org.example.elements;

import ro.altom.altunitytester.AltUnityObject;

import static org.example.DriverSettings.altUnityDriver;

public class Menu {

    public static AltUnityObject waitForElement(String name){

        return altUnityDriver.waitForElement(name, "Camera", true, 3, 0.5);
    }

    public static AltUnityObject waitForElement(String name, boolean enabled){

        return altUnityDriver.waitForElement(name, "Camera", enabled, 3, 0.5);
    }

    public static AltUnityObject getPlay(){

        return waitForElement("Play");
    }

    public static AltUnityObject getSettings(){

        return waitForElement("Settings");
    }

    public static AltUnityObject getNext(){

        return waitForElement("Next");
    }

    public static AltUnityObject getPrev(){

        return waitForElement("Prev");
    }

    public static AltUnityObject getBack(){

        return waitForElement("Back");
    }

    public static AltUnityObject getCloseMe(){

        return waitForElement("CloseMe", false);
    }

    public static AltUnityObject getClose(){

        return waitForElement("Close");
    }

    public static AltUnityObject getLevelComplete(){

        return waitForElement("LevelComplete", false);
    }
}
