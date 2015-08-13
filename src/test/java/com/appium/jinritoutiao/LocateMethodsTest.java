package com.appium.jinritoutiao;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}
//    //byXPath
//    @Test
//    public void byXPath() {
//    	
//    	driver.findElementByXPath("//android.widget.ListView/android.widget.FrameLayout[1]").click();//偶尔点到的其实不是第一个，所以用uiautomator会更靠谱
//    	driver.findElementByXPath("//android.widget.TextView[contains(@text,'写评论')]").click();
//    	WebElement title = driver.findElementByXPath("//android.widget.TextView[contains(@text,'登录今日头条')]");
//    	title.isDisplayed();
//    }
//    //byAndroidUIAutomator
//    @Test
//    public void byAndroidUIAutomatorTest(){
//        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ss.android.article.news:id/category_text\").text(\"热点\")").click();//进入热点
//        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.ss.android.article.news:id/view_pager\")).scrollIntoView(new UiSelector().resourceId(\"com.ss.android.article.news:id/title\").text(\"全球最大婚外情网站被黑\"))").click();
//	    driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ss.android.article.news:id/action_view_comment\")").click();//点击评论
//	    WebElement hotTopic = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ss.android.article.news:id/section_text\").text(\"热门评论\")");
//	    hotTopic.isDisplayed();//验证界面上有热门评论模块即为成功
//	    
//    }
    //byClassName进入今日头条->个人信息->更多登录方式->注册新账号  的流程
    @Test
    public void byClassNameTest() throws InterruptedException{
    	
    	WebDriverWait wait = new WebDriverWait(driver,20);
    	wait.until(new ExpectedCondition<WebElement>(){

			public WebElement apply(WebDriver d) {
				// TODO Auto-generated method stub
				return d.findElement(By.id("com.ss.android.article.news:id/category_text"));
			}
    	}).isDisplayed();
    	
    	//使用wait.until方法替代之前为了确保到达主页而做的点击
    	//driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ss.android.article.news:id/category_text\").text(\"热点\")").click();//这个步骤完全是为了确保应用启动到首页了，而不是还在启动页


    	driver.findElementByClassName("android.widget.ImageView").click();// 个人中心是整个页面的第一个ImageView	
		List<WebElement> textViewList = driver.findElementsByClassName("android.widget.TextView");
		WebElement moreType = null;
		for (WebElement textView : textViewList) {
			if (textView.getText().equals("更多登录方式")) {
				moreType = textView;
				break;
			}
		}
		moreType.click();
		List<WebElement> buttonList = driver.findElementsByClassName("android.widget.Button");	//注册新账号为当前页面第二个button，且只有2个button
		buttonList.get(1).click();

		driver.findElementByClassName("android.widget.EditText").sendKeys("12345678910");// 注册整个页面只有一个EditText
		driver.findElementByClassName("android.widget.Button").click();// 此界面只有一个button“下一步”
		driver.getPageSource().contains("手机号注册");//这一步输入不正确的手机号码时弹出框是toast，appium无法校验,所以验证界面还停留在当前页面即为成功
     
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
  

}
