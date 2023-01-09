package Mindpool.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".sk-button-text")
	List<WebElement> nextButton;

	public void waitForWebElementToEnable(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		for(int i=0; i<=3;i++){
			  try{
			     wait.until(ExpectedConditions.elementToBeClickable(findBy));
			     break;
			  }
			  catch(Exception e){
			     System.out.println(e.getMessage());
			  }
			}
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void clickNextButton() throws InterruptedException
	{
		for(int i=0; i<nextButton.size();i++)
		{
			if(nextButton.get(i).getText().equalsIgnoreCase("Next"))
			{
				nextButton.get(i).click();
				break;
			}
			
			else if(nextButton.get(i).getText().equalsIgnoreCase("Proceed"))
			{
				nextButton.get(i).click();
				break;
			}
			
			else if(nextButton.get(i).getText().equalsIgnoreCase("Confirm & Pay"))
			{
				nextButton.get(i).click();
				break;
			}
		}
		Thread.sleep(4000);
	}
	
	
}
