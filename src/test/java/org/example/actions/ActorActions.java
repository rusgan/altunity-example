package org.example.actions;

import org.example.Properties;
import org.example.elements.Gamepad;
import org.example.elements.Level;
import org.example.elements.Menu;
import ro.altom.altunitytester.AltUnityObject;

import java.util.List;

import static org.example.elements.Gamepad.*;
import static org.example.elements.Level.getKeys;
import static org.example.elements.Level.getSortedDoors;
import static org.example.elements.Menu.getClose;

public class ActorActions {

    public static void moveToKey(AltUnityObject key){

        if(Level.getActor().getWorldX() < key.getWorldX()){

            while(Level.getActor().getWorldX() < key.getWorldX() - Properties.KEY_SIZE/2){

                tap(Gamepad.getRight());
            }
        }
        else {

            while(Level.getActor().getWorldX() < key.getWorldX() + Properties.KEY_SIZE/2){

                tap(Gamepad.getLeft());
            }
        }
        if(Level.getActor().getWorldY() < key.getWorldY()){

            while(Level.getActor().getWorldY() < key.getWorldY()){

                tap(Gamepad.getUp());
            }
        }
    }

    public static void moveToDoor(AltUnityObject door){

        if(Level.getActor().getWorldX() < door.getWorldX()){

            while(Level.getActor().getWorldX() < door.getWorldX() - Properties.DOOR_SIZE){

                tap(Gamepad.getRight());
            }
        }
        else {

            while(Level.getActor().getWorldX() > door.getWorldX() + Properties.DOOR_SIZE){

                tap(Gamepad.getLeft());
            }
        }
    }

    public static void interact(){

        tap(Gamepad.getInteract());
    }

    public static void openDoors(){

        List<AltUnityObject> doors = getSortedDoors();

        for(AltUnityObject door : doors){

            AltUnityObject key = getKeys()
                    .stream()
                    .filter(ky -> ky.getComponentProperty("Lock", "Type").equals(door.getComponentProperty("Lock", "Type")))
                    .findAny()
                    .orElse(null);
            moveToKey(key);
            interact();
            moveToDoor(door);
            interact();
        }
    }

    public static void moveToExit(){

        while(!Menu.getCloseMe().isEnabled() & !Menu.getLevelComplete().isEnabled()){

            if(Level.getActor().getWorldX() < Level.getExit().getWorldX()){

                tap(Gamepad.getRight());
            }
            else {

                tap(Gamepad.getLeft());
            }
        }
    }

    public static void closeLevel(){

        moveToExit();
        if(Menu.getCloseMe().isEnabled()){
            Menu.getClose().tap();
            Menu.getNext().tap();
        }
        else {
            Menu.getNext().tap();
        }
    }
}
