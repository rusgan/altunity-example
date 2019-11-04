package org.example.elements;

import ro.altom.altunitytester.AltUnityObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.example.DriverSettings.altUnityDriver;

public class Level {

    public static List<AltUnityObject> getKeys(){

        return Arrays.asList(altUnityDriver.findElementsWhereNameContains("Key"));
    }

    public static AltUnityObject[] getDoors(){

        return altUnityDriver.findElementsWhereNameContains("Door");
    }

    public static List<AltUnityObject> getSortedDoors(){

        List<AltUnityObject> doors = Arrays.asList(altUnityDriver.findElementsWhereNameContains("Door"));
        if(getActor().getWorldX() < getExit().getWorldX()){

            doors.sort(Comparator.comparing(AltUnityObject::getWorldX));
        }
        else {

            doors.sort(Comparator.comparing(AltUnityObject::getWorldX).reversed());
        }
        return doors;
    }

    public static AltUnityObject getMap(){

        return altUnityDriver.waitForElementWhereNameContains("Map");
    }

    public static AltUnityObject getExit(){

        return altUnityDriver.waitForElementWhereNameContains("Exit");
    }

    public static AltUnityObject getActor(){

        return altUnityDriver.waitForElementWhereNameContains("Actor");
    }

    public static int getLevel(){

        return Integer.parseInt(getMap().getComponentProperty("Map", "Level"));
    }

    public static int getTotalLevels(){

        return Integer.parseInt(altUnityDriver.findElement("GameManager").getComponentProperty("GameManager", "TOTAL_LEVELS"));
    }
}
