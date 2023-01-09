package Mindpool.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Mindpool.AbstractComponents.AbstractComponent;

public class TripDetailsPage extends AbstractComponent{

	WebDriver driver;
	
	public TripDetailsPage(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@title='Destination Country']")
	WebElement destinationDropdown;
	
	@FindBy(css=".ui-menu-item-wrapper")
	List<WebElement> countryList;
	
	@FindBy(xpath="//label[@for='Travel-CustomerDetails-ResidentUAE']")
	WebElement residentLabel;
	
	@FindBy(xpath="//label[contains(@for, 'Travel-CustomerDetails-ResidentUAE')]/span")
	WebElement residentType;
	
	@FindBy(xpath="//label[contains(@for, 'Travel-TripDetails-CoverType')]/span")
	WebElement coverTypeTrip;
	
	@FindBy(xpath="//label[contains(@for, 'Travel-TripDetails-CoverageOption')]/span")
	WebElement coverageOptionType;
	
	@FindBy(id="Travel-TripDetails-Adults-button")
	WebElement adultDropdown;
	
	@FindBy(css="div.ui-menu-item-wrapper")
	List<WebElement> travellerList;
	
	@FindBy(id="Travel-TripDetails-StartDate-sk-datepicker")
	WebElement departureDatePicker;
	
	@FindBy(id="Travel-TripDetails-EndDate-sk-datepicker")
	WebElement returnDatePicker;
	
	@FindBy(css=".ui-datepicker-month")
	WebElement monthPicker;
	
	@FindBy(css=".ui-state-default")
	List<WebElement> datePicker;
	
	@FindBy(xpath="//input[contains(@id ,'Travel-CustomerDetails-ResidentUAE') and @checked='checked']")
	WebElement residentCheck;
	
	@FindBy(xpath="//input[@title='Destination Country']")
	WebElement destinationCheck;
	
	@FindBy(id="Travel-TripDetails-StartDate")
	WebElement departureDateCheck;
	
	@FindBy(id="Travel-TripDetails-EndDate")
	WebElement returnDateCheck;
	
	@FindBy(xpath="//span/input[contains(@id ,'Travel-TripDetails-CoverType') and @checked='checked']/following-sibling::label/span")
	WebElement coverTypeCheck;
	
	@FindBy(xpath="//span/input[contains(@id ,'Travel-TripDetails-CoverageOption') and @checked='checked']/following-sibling::label/span")
	WebElement coverageOptionCheck;
	
	@FindBy(xpath="//span[@id='Travel-TripDetails-Adults-button']/span[@class='ui-selectmenu-text']")
	WebElement adultCount;
	
	
	public void selectCountry(String country)
	{
		destinationDropdown.sendKeys(country);
		for(int i=0; i<countryList.size(); i++)
		{
			if(countryList.get(i).getText().equalsIgnoreCase(country))
			{
				countryList.get(i).click();
				break;
			}
		}
		waitForWebElementToAppear(residentLabel);
	}
	
	public void selectResident(String res) throws InterruptedException
	{
		if(residentType.getText().equalsIgnoreCase(res))
		{
			residentType.click();
		}
		Thread.sleep(2000);
	}
	
	public void selectCoverType(String coverType) throws InterruptedException
	{
		if(coverTypeTrip.getText().equalsIgnoreCase(coverType))
		{
			coverTypeTrip.click();
		}
		Thread.sleep(2000);
	}
	
	public void selectCoverageOption(String coverageOption) throws InterruptedException
	{
		if(coverageOptionType.getText().equalsIgnoreCase(coverageOption))
		{
			coverageOptionType.click();
		}
		Thread.sleep(2000);
	}
	
	public void addTraveller(String type, String number) throws InterruptedException
	{
		if(type.equalsIgnoreCase("Adult"))
		{
			adultDropdown.click();
			for(int i=0; i<travellerList.size(); i++)
			{
				if(travellerList.get(i).getText().equalsIgnoreCase(number))
				{
					travellerList.get(i).click();
					break;
				}
			}
		}
		Thread.sleep(2000);
	}
	
	public void selectDate(String type, String month, String date) throws InterruptedException
	{
		if(type.equalsIgnoreCase("Departure"))
		{
			departureDatePicker.click();
		}
		
		else
			returnDatePicker.click();
		
		Select s = new Select(monthPicker);
		s.selectByVisibleText(month);
		
		for(int i=0; i<datePicker.size(); i++)
		{
			if(datePicker.get(i).getText().equalsIgnoreCase(date))
			{
				datePicker.get(i).click();
				break;
			}
		}
		
		Thread.sleep(3000);
	}
	
	public void selectDepartureDate(String depmonth, String depdate)
	{
		departureDatePicker.click();
		Select s = new Select(monthPicker);
		s.selectByVisibleText(depmonth);
		
		for(int i=0; i<datePicker.size(); i++)
		{
			if(datePicker.get(i).getText().equalsIgnoreCase(depdate))
			{
				datePicker.get(i).click();
				break;
			}
		}
		
	}
	
	public void selectReturnDate(String returnmonth, String returndate)
	{
		returnDatePicker.click();
		Select s = new Select(monthPicker);
		s.selectByVisibleText(returnmonth);
		
		for(int i=0; i<datePicker.size(); i++)
		{
			if(datePicker.get(i).getText().equalsIgnoreCase(returndate))
			{
				datePicker.get(i).click();
				break;
			}
		}
		
	}
	
	
	
	public String getResident()
	{
		return residentCheck.getAttribute("value");
	}
	
	public String getDestination()
	{
		return destinationCheck.getAttribute("value");
	}
	
	public String getDate(String type)
	{
		String wholedate;
		String res = null;
		if(type.equalsIgnoreCase("Departure"))
		{
			wholedate = departureDateCheck.getAttribute("value");
		}
		
		else
			 wholedate = returnDateCheck.getAttribute("value");
	
		String[] dateArray = wholedate.split("/");
		for (int i = 0; i < dateArray[0].length(); i++) {
	        if (dateArray[0].charAt(i)!= '0') {
	            // return the remaining string
	            res = dateArray[0].substring(i);
	            return res;
	        }
		}
		return res;
		
		
	}
	
	public String getMonth(String type)
	{
		String wholedate;
		String res = null;
		if(type.equalsIgnoreCase("Departure"))
		{
			wholedate = departureDateCheck.getAttribute("value");
		}
		
		else
			 wholedate = returnDateCheck.getAttribute("value");
	
		String[] dateArray = wholedate.split("/");
		for (int i = 0; i < dateArray[1].length(); i++) {
	        if (dateArray[1].charAt(i)!= '0') {
	            // return the remaining string
	            res = dateArray[1].substring(i);
	            return res;
	        }
		}
		return res;
	}
	
	public String getCoverType()
	{
		return coverTypeCheck.getText();
	}
	
	public String getCoverageOption()
	{
		return coverageOptionCheck.getText();
	}
	
	public String getTravellerCount(String type)
	{
		String travellerCount = null;
		if(type.equalsIgnoreCase("Adult"))
		{
			travellerCount= adultCount.getText();
		}
		return travellerCount;
	}
}

