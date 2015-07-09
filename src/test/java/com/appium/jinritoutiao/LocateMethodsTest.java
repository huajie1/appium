package com.appium.jinritoutiao;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LocateMethodsTest {
	
	private AndroidDriver driver;
	
	@Before
	public void setUp() throws Exception {
		// set up appium
		// InitialDevice.initialDevice();
		File app = new File("apps/jinritoutiao_465.apk");
		DesiredCapabilities capa = new DesiredCapabilities();
		capa.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capa.setCapability(MobileCapabilityType.DEVICE_NAME, "Vivo X3L");
		capa.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.3");
		capa.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capa.setCapability("appPackage", "com.ss.android.article.news");
		capa.setCapability("appActivity","com.ss.android.article.news.activity.SplashActivity");		
		capa.setCapability("noReset",true);		
		driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),capa);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);

	}
   
    @Test
    public void byXPath() {
    	
    	driver.findElementByXPath("//android.widget.ListView/android.widget.FrameLayout[1]").click();//ż���㵽����ʵ���ǵ�һ����������uiautomator�������
    	driver.findElementByXPath("//android.widget.TextView[contains(@text,'д����')]").click();
    	WebElement title = driver.findElementByXPath("//android.widget.TextView[contains(@text,'��¼����ͷ��')]");
    	title.isDisplayed();
    }
    
    @Test
    public void byAndroidUIAutomatorTest(){
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ss.android.article.news:id/category_text\").text(\"�ȵ�\")").click();//�����ȵ�
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.ss.android.article.news:id/view_pager\")).scrollIntoView(new UiSelector().resourceId(\"com.ss.android.article.news:id/title\").text(\"��������֤��˾�ṩ�����ٴ���\"))").click();
	    driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ss.android.article.news:id/action_view_comment\")").click();//�������
	    WebElement hotTopic = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ss.android.article.news:id/section_text\").text(\"��������\")");
	    hotTopic.isDisplayed();//��֤����������������ģ�鼴Ϊ�ɹ�
	    
    }
     
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}


}
