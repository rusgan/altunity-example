package org.example.tests;

import org.example.actions.ActorActions;
import org.example.actions.GameActions;
import org.example.actions.MenuActions;
import org.example.elements.Level;
import org.example.elements.Menu;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.example.DriverSettings.initDrivers;
import static org.example.DriverSettings.stopDrivers;
import static org.assertj.core.api.Assertions.*;
import static org.example.actions.GameActions.relaunchApp;
import static org.example.actions.GameActions.resetApp;

public class Tests {


    @BeforeAll
    static void setUp() throws IOException, InterruptedException {

        initDrivers();
        GameActions.loadMain();
    }

    @AfterAll
    static void tearDown(){

        stopDrivers();
    }
    @ParameterizedTest
    @ValueSource(strings = {"ActorA", "ActorB"})
    void completeLevels(String actorName) throws InterruptedException {

        MenuActions.tapPlay();
        MenuActions.selectActor(actorName);
        int currentLevel = Level.getLevel();
        for (int i = currentLevel; i <= Level.getTotalLevels(); i++) {

            ActorActions.openDoors();
            ActorActions.closeLevel();
        }
        assertThat(Menu.getMenu().isEnabled()).isTrue();
        resetApp();
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void saving(int level) throws InterruptedException {

        MenuActions.tapPlay();
        for (int i = 1; i <= level; i++) {

            ActorActions.openDoors();
            ActorActions.closeLevel();
        }
        int currentLevel = Level.getLevel();
        relaunchApp();
        GameActions.loadMain();
        MenuActions.tapPlay();
        assertThat(currentLevel).isEqualTo(Level.getLevel());
    }
}
