package com.appium.jinritoutiao;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class HybridAppTest {
	
	private AndroidDriver driver;
	double platform_version = 4.4;

	@Before
	public void setUp() throws Exception {
		// set up appium
		// InitialDevice.initialDevice();
		File app = new File("apps/PAWetalk350.apk");
		DesiredCapabilities capa = new DesiredCapabilities();
		capa.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capa.setCapability(MobileCapabilityType.DEVICE_NAME, "MI4");
		//capa.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
		capa.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capa.setCapability("appPackage", "com.pingan.wetalk");
		capa.setCapability("appActivity","com.pingan.wetalk.activity.SplashActivity");
		driver = new AndroidDriver(new URL("http://localhost:4725/wd/hub"),capa);
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        
		if(platform_version<4.4){
			capa.setCapability("automationName", "selendroid");
			
		}
	}

	@Test
    public void webViewTest() throws InterruptedException{		
		

		
		if(platform_version<4.4){
			driver.findElement(By.xpath("//Button[@id='login_btn']")).click();
			driver.findElementByXPath("//android.widget.EditText[1]").sendKeys("huajie5");
			driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("888888");
			driver.findElementByXPath("//android.widget.Button[contains(@text,'登录')]").click();
			driver.findElementByXPath("//android.widget.ImageView[contains(@text,'我')]").click();
			driver.findElementByXPath("//android.widget.TextView[contains(@text,'互动区')]").click();

			
		}
		else{
			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"登录\")").click();		
			driver.findElementByAndroidUIAutomator("new UiSelector().descriptionContains(\"天下通号/手机号码\")").sendKeys("huajie5");
			driver.findElementByAndroidUIAutomator("new UiSelector().descriptionContains(\"请输入密码\")").sendKeys("888888");
			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"登录\")").click();
			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"我\")").click();
			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"互动区\")").click();
			
		}	
		Thread.sleep(6000);
//		Set<String> contextNames = driver.getContextHandles();
//		for (String contextName : contextNames) {
//			System.out.println(contextName);
//			if (contextName.contains("WEBVIEW_com.pingan.wetalk")) {
//				driver.context(contextName);
//				break;
//			}
//		}
//		List<WebElement> buttonList = driver.findElementsByClassName("activated");
//		buttonList.get(3).click();
		driver.findElementByAndroidUIAutomator("new UiSelector().description(\"寿险钻石峰会\")").click();;
		WebElement title = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.pingan.wetalk:id/headerView\")");
        assert title.getText().equals("钻石论坛交流区");
        
		Thread.sleep(10000);

	}
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
