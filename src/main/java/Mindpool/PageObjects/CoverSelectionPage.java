package Mindpool.PageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Mindpool.AbstractComponents.AbstractComponent;

public class CoverSelectionPage extends AbstractComponent{
	
WebDriver driver;
	
	public CoverSelectionPage(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="input[value='elite']")
	WebElement eliteCoverage;

	@FindBy(css=".fileinput-button")
	WebElement addPassportCopy;
	
	@FindBy(xpath="//label[contains(@for,'Travel-CustomerDetails-ConfirmationReview')]")
	WebElement confirmationCheckbox;
	
	@FindBy(id="Travel-TripDetails-Traveler°1-FirstName")
	WebElement passengerFirstName;
	
	@FindBy(id="Travel-TripDetails-Traveler°1-LastName")
	WebElement passengerLastName;
	
	@FindBy(id="Travel-TripDetails-Traveler°1-DateOfBirth-sk-datepicker")
	WebElement passengerDateofBirthPicker;
	
	@FindBy(id="Travel-TripDetails-Traveler°1-TravellersNationalityFlexdata--label")
	WebElement passengerNationality;
	
	@FindBy(id="Travel-TripDetails-Traveler°1-PassportNo")
	WebElement passengerPassportNo;
	
	@FindBy(css=".ui-datepicker-year")
	WebElement yearPicker;
	
	@FindBy(css=".ui-datepicker-month")
	WebElement monthPicker;
	
	@FindBy(css=".ui-state-default")
	List<WebElement> datePicker;
	
	@FindBy(css=".ui-menu-item-wrapper")
	List<WebElement> passengerNationalitySelect;
	
	
	public void selectCoverType(String coverSelect)
	{
		if(coverSelect.equalsIgnoreCase("Elite"))
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			 js.executeScript("arguments[0].click();", eliteCoverage);
		}
		
	}
	
	public void addFirstName(String firstName) throws InterruptedException
	{
		passengerFirstName.sendKeys(firstName);
		Thread.sleep(3000);
	}
	
	public void selectDob(String dobYear, String dobMonth, String dobDate) throws InterruptedException
	{
		passengerDateofBirthPicker.click();
		Thread.sleep(3000);
		
		yearPicker.click();
		Select s = new Select(yearPicker);
		s.selectByValue(dobYear);
		
		monthPicker.click();
		Select s1 = new Select(monthPicker);
		s1.selectByVisibleText(dobMonth);
		
		for(int i=0; i<datePicker.size(); i++)
		{
			if(datePicker.get(i).getText().equalsIgnoreCase(dobDate))
			{
				datePicker.get(i).click();
				break;
			}
		}

		Thread.sleep(3000);
	}
	
	public void addNationality(String country) throws InterruptedException
	{
		passengerNationality.click();
		Thread.sleep(3000);
		passengerNationality.sendKeys(country);
		Thread.sleep(1000);
		
		for(int i=0;i<passengerNationalitySelect.size();i++)
		{
			if(passengerNationalitySelect.get(i).getText().equalsIgnoreCase(country))
			{
				passengerNationalitySelect.get(i).click();
				break;
			}
		}
		
		Thread.sleep(2000);
	}
	
	public void addPassportNo(String passportNo) throws InterruptedException
	{
		passengerPassportNo.click();
		Thread.sleep(3000);
		passengerPassportNo.sendKeys(passportNo);
		Thread.sleep(3000);
	}
	
	public void addLastName(String lastName) throws InterruptedException
	{
		passengerLastName.click();
		Thread.sleep(3000);
		passengerLastName.sendKeys(lastName);
		Thread.sleep(3000);
	}
	
	public void addPassport() throws InterruptedException
	{
		addPassportCopy.click();
		Thread.sleep(5000);
		
	}
	
	public void selectConfirmationCheckbox() throws InterruptedException
	{
		confirmationCheckbox.click();
		Thread.sleep(3000);
	}
	

}
