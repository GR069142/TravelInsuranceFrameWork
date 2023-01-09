package Mindpool.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Mindpool.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	
WebDriver driver;
	
	public PaymentPage(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="a[title='Payment']")
	WebElement paymentSection;
	
	@FindBy(css="div.sk-payment")
	WebElement paymentSectionExpand;
	
	@FindBy(id="creditCardNumber")
	WebElement cardNumber;
	
	@FindBy(id="expiryMonthCreditCard")
	WebElement expiryMonth;
	
	@FindBy(id="expiryYearCreditCard")
	WebElement expiryYear;
	
	@FindBy(id="CVVNumberCreditCard")
	WebElement cvv;
	
	@FindBy(css="span.primary-button-text")
	WebElement makePaymentButton;

	@FindBy(css="h1")
	WebElement finalPage;
	
	public void selectPaymentSection()
	{
		paymentSection.click();
	}
	
	public void paymentSectionExpand()
	{
		if(!paymentSectionExpand.isDisplayed())
		{
			selectPaymentSection();
		}
			
	}
	
	public void enterCreditCardNumber(String creditCardNo)
	{
		cardNumber.sendKeys(creditCardNo);
	}
	
	public void enterExpiryMonth(String month)
	{
		Select s = new Select(expiryMonth);
		s.selectByValue(month);
	}
	
	public void enterExpiryYear(String year)
	{
		Select s = new Select(expiryYear);
		s.selectByValue(year);
	}
	
	public void enterCvv(String cvvNo)
	{
		cvv.sendKeys(cvvNo);
	}
	
	public void makePayment()
	{
		makePaymentButton.click();
	}
	
	public String finalPage()
	{
		return finalPage.getText();
	}
	
}
