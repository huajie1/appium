package com.appium.jinritoutiao;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobleWebAppTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		// set up appium
		DesiredCapabilities capa = new DesiredCapabilities();
		capa.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capa.setCapability(MobileCapabilityType.DEVICE_NAME, "MI4");
		capa.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		capa.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
		driver = new AndroidDriver(new URL("http://localhost:4725/wd/hub"),capa);
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        
	}

	@Test
	//百度首页->个人中心->登录->验证登录失败
    public void mobileWebAppTest() throws InterruptedException{	
		driver.get("http://www.baidu.com");
//		driver.findElement(By.id("login")).click();;
		driver.findElement(By.xpath("//*[@id='login']")).click();
		
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		assert loginButton.getText() == "登录";
		driver.findElement(By.id("login-username")).sendKeys("huajie");
		driver.findElement(By.id("login-password")).sendKeys("huajie");
		loginButton.click();
		WebElement loginFailed= driver.findElement(By.id("error"));
		assert loginFailed.getText() =="您输入的密码有误";
		Thread.sleep(5000);

	}
	
	@Test
    //将同类的抽出写成功公共的定位
    public void mobileWebTest() throws InterruptedException{
		driver.get("http://www.baidu.com");
//		driver.findElement(By.xpath("//a[text()='新闻']")).click();
//		Thread.sleep(5000);
//		driver.navigate().back();
//		driver.findElement(By.xpath("//a[text()='小说']")).click();
//		Thread.sleep(5000);
//		driver.navigate().back();
//		driver.findElement(By.xpath("//a[text()='视频']")).click();
//		Thread.sleep(5000);

        for(int i=1;i<6;i++){
        	driver.findElement(By.xpath("//*[@id='navs']/a["+i+"]")).click();
        	Thread.sleep(5000);
        	driver.navigate().back();
        }
    	
    	
    }
    
    @Test
    public void searchAppium(){
    	driver.get("http://www.baidu.com");
    	driver.findElement(By.xpath("//*[@id='index-kw']")).sendKeys("appium");
    	driver.findElement(By.xpath("//*[@id='index-bn']")).click();
    	assert driver.getPageSource().contains("appium中文简明教程");
    	
    }
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
