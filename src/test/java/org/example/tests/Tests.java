package org.example.tests;

import org.example.actions.ActorActions;
import org.example.actions.GameActions;
import org.example.actions.MenuActions;
import org.example.elements.Level;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.example.DriverSettings.initDrivers;
import static org.example.DriverSettings.stopDrivers;

public class Tests {


    @BeforeAll
    static void setUp() throws MalformedURLException, InterruptedException {

        initDrivers();
    }

    @AfterAll
    static void tearDown(){

        stopDrivers();
    }
    @Test
    void some(){

        GameActions.loadMain();
        MenuActions.tapPlay();

        int currentLevel = Level.getLevel();
        for (int i = currentLevel; i <= Level.getTotalLevels(); i++) {

            ActorActions.openDoors();
            ActorActions.closeLevel();
        }
    }
}
