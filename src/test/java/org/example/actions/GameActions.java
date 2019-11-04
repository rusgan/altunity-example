package org.example.actions;

import static org.example.DriverSettings.*;

public class GameActions {

    public static void loadMain(){

        altUnityDriver.loadScene("main");
    }

    public static void resetApp() throws InterruptedException {

        appiumDriver.resetApp();
        waitForApp();
    }

    public static void relaunchApp() throws InterruptedException {

        appiumDriver.closeApp();
        appiumDriver.launchApp();
        waitForApp();
    }
}
