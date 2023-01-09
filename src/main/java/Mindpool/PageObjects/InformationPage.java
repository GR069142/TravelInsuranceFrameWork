package Mindpool.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Mindpool.AbstractComponents.AbstractComponent;


public class InformationPage extends AbstractComponent{

WebDriver driver;
	
	public InformationPage(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".ui-selectmenu-text")
	List<WebElement> menuDropdown;
	
	@FindBy(xpath="//ul[@id='Travel-CustomerDetails-Title-menu']/li/div")
	List<WebElement> titleDropdown;
	
	@FindBy(id="Travel-CustomerDetails-FirstName")
	WebElement firstName;
	
	@FindBy(id="Travel-CustomerDetails-LastName")
	WebElement lastName;
	
	@FindBy(id="Travel-CustomerDetails-Email")
	WebElement email;
	
	@FindBy(id="Travel-CustomerDetails-PhoneNumber")
	WebElement phoneNumber;
	
	public void selectTitle(String title) throws InterruptedException
	{
		menuDropdown.get(0).click();
		for(int i=0; i<titleDropdown.size(); i++)
		{
			if(titleDropdown.get(i).getText().equalsIgnoreCase(title))
			{
				titleDropdown.get(i).click();
				break;
			}
		}
		
		waitForWebElementToEnable(firstName);
		Thread.sleep(2000);
		
	}
	
	public void enterFirstName(String name)
	{
		firstName.sendKeys(name);
		waitForWebElementToEnable(lastName);
	}
	
	public void enterLastName(String name) throws InterruptedException
	{
		lastName.click();
		Thread.sleep(2000);
		lastName.sendKeys(name);
		waitForWebElementToEnable(email);
	}
	
	public void enterEmail(String emailId) throws InterruptedException
	{
		email.click();
		Thread.sleep(2000);
		email.sendKeys(emailId);
		waitForWebElementToEnable(phoneNumber);
	}
	
	public void enterPhoneNumber(String phoneNo) throws InterruptedException
	{
		phoneNumber.click();
		Thread.sleep(2000);
		phoneNumber.sendKeys(phoneNo);
	}
	
	public String getFirstName()
	{
		return firstName.getAttribute("value");
	}
	
	public String getLastName()
	{
		return lastName.getAttribute("value");
	}
	
	public String getEmail()
	{
		return email.getAttribute("value");
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber.getAttribute("value");
	}
	
}
