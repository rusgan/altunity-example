package org.example;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import ro.altom.altunitytester.AltUnityDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSettings {

    public static AltUnityDriver altUnityDriver;
    public static AndroidDriver appiumDriver;
    public static AppiumDriverLocalService service;


    public static void initDrivers() throws IOException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("avd","pixel2");
        capabilities.setCapability("deviceName", "pixel2");
        capabilities.setCapability("app", Properties.APP);
        capabilities.setCapability("deviceOrientation", "landscape");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("clearDeviceLogsOnStart", true);
        capabilities.setCapability("noReset","false");

        appiumDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        appiumDriver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        waitForApp();
        AltUnityDriver.setupPortForwarding("android", "", 13000, 13000);
        reverse();
        altUnityDriver = new AltUnityDriver("127.0.0.1", 13000);
    }

    public static void stopDrivers(){

        altUnityDriver.stop();
        appiumDriver.quit();
    }

    public static void waitForApp() throws InterruptedException {

        //TODO breake/timeout/clearlogs
        LogEntries logs = appiumDriver.manage().logs().get("logcat");

        LogEntry log = logs.getAll().stream()
                .filter(entry -> entry.getMessage().contains("AltUnity Driver started"))
                .findAny()
                .orElse(null);

        if(log == null){

            Thread.sleep(500);
            waitForApp();
        }
    }

    static void reverse() throws InterruptedException, IOException {

        Runtime.getRuntime().exec("adb reverse tcp:8200 tcp:8200");
        Thread.sleep(1000L);
    }
}
