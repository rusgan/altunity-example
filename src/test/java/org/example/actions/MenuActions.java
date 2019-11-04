package org.example.actions;

import org.example.elements.Menu;

public class MenuActions {

    public static void tapPlay(){

        Menu.getPlay().tap();
    }

    public static void tapNext(){

        Menu.getNext().tap();
    }

    public static void tapPrev(){

        Menu.getPrev().tap();
    }

    public static void tapBack(){

        Menu.getBack().tap();
    }
}
