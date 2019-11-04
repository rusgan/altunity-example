package org.example;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import ro.altom.altunitytester.AltUnityDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSettings {

    public static AltUnityDriver altUnityDriver;
    public static AndroidDriver appiumDriver;
    public static AppiumDriverLocalService service;


    public static void initDrivers() throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("avd","pixel2");
        capabilities.setCapability("deviceName", "pixel2");
        capabilities.setCapability("app", Properties.APP);
        capabilities.setCapability("deviceOrientation", "landscape");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("clearDeviceLogsOnStart", true);
        capabilities.setCapability("noReset","true");

        appiumDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        appiumDriver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        Thread.sleep(10000);
        //AltUnityDriver.setupPortForwarding("android", "", 13000, 13000);
        altUnityDriver = new AltUnityDriver("127.0.0.1", 13000);
    }

    public static void stopDrivers(){

        altUnityDriver.stop();
        appiumDriver.quit();
    }
}
