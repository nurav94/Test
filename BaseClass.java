package testing;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;




public class BaseClass {

	public static WebDriver driver;


	public WebDriver dummyDriver;


	public BaseClass() {
		try {
			dummyDriver = getdriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void GetScreenshots() throws WebDriverException, InterruptedException {
		String timestamp = new SimpleDateFormat("MM_dd__hh_mm_ss").format(new Date());
		
		String loc =System.getProperty("user.dir");
		System.out.println(loc);
		
		
		File testFile = new File(loc + "/screenshots");
		
		
		if(!testFile.exists()) System.out.println( "\n\ncreated " + testFile.mkdirs());
		
		File sourcefile=((TakesScreenshot) BaseClass.getdriver()).getScreenshotAs(OutputType.FILE);

		//File destinationfile= new File("C:\\Users\\demo\\.jenkins\\workspace\\mcaasMaven\\Screeshots\\",timestamp+".png"); 
		
		File destinationfile= new File(loc+"/screenshots", timestamp+".png");
		
//		File destinationfile= new File("..\\..\\..\\..\\Screenshots\\",timestamp+".png");
		System.out.println(destinationfile.toString());
		
		try {
			FileHandler.copy(sourcefile,destinationfile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		

		TestListeners.extentTest.get().addScreenCaptureFromPath("/job/MCaaS/ws/screenshots/"+timestamp+".png");
		
//		TestListeners.extentTest.get().addScreenCaptureFromPath(destinationfile.toString());
		
//		TestListeners.extentTest.get().addScreenCaptureFromPath("http://localhost:8080/job/mcaasMaven/ws/",timestamp+".png");

	}


	public static WebDriver getdriver() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");


		if(driver == null)
		{
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		Dimension dimension = new Dimension(1382,744);
			driver.manage().window().setSize(dimension);
			Thread.sleep(3000);
			driver.get("http://mcaassales.novamcaas.com:8080/MCaaS/#/login");
			return driver;

		}


		else {
			return driver;
		}
	}



	public static void closeWebDriver() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}




	@BeforeSuite

	public static void before() throws InterruptedException{



		getdriver();

	}

	@AfterSuite
	public void after() throws InterruptedException

	{


//		System.out.println("All Completed");
	
		 Thread.sleep(10000); 
		 driver.close();
		 
	}


}
