package Mindpool.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Mindpool.PageObjects.InformationPage;

public class BaseTest {

	public WebDriver driver;
	public InformationPage informationPage;
	Properties prop;

	public WebDriver initializeDriver() throws IOException

	{
		// properties class

		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//Mindpool//Resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\R Girija\\Documents\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"//Users//R Girija//documents//geckodriver");
			driver = new FirefoxDriver();
		
		} else if (browserName.equalsIgnoreCase("edge")) {
			
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;

	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public InformationPage launchApplication() throws IOException
	{
		
		 driver = initializeDriver();
		 informationPage = new InformationPage(driver);
		 driver.get(prop.getProperty("url"));
		return informationPage;
	
		
	}
	
	@AfterMethod(alwaysRun=true)
	
	public void tearDown()
	{
		driver.close();
	}
	
}
