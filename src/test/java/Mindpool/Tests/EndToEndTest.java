package Mindpool.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Mindpool.PageObjects.CoverSelectionPage;
import Mindpool.PageObjects.PaymentPage;
import Mindpool.PageObjects.TripDetailsPage;
import Mindpool.TestComponents.BaseTest;

public class EndToEndTest extends BaseTest{

	@Test(dataProvider="getData")
	public void submitInsurance(HashMap<String,String> input) throws InterruptedException, IOException
	{
		informationPage.selectTitle(input.get("title"));
		informationPage.enterFirstName(input.get("firstName"));
		informationPage.enterLastName(input.get("lastName"));
		informationPage.enterEmail(input.get("email"));
		informationPage.enterPhoneNumber(input.get("phoneNumber"));
		informationPage.clickNextButton();
		
		TripDetailsPage tripDetailsPage = new TripDetailsPage(driver);
		tripDetailsPage.selectCountry(input.get("country"));
		tripDetailsPage.selectResident(input.get("resident"));
		tripDetailsPage.selectCoverType(input.get("coverType"));
		tripDetailsPage.selectCoverageOption(input.get("coverageOption"));
		tripDetailsPage.addTraveller(input.get("travellerType"), input.get("travellerNumber"));
		tripDetailsPage.selectDate("Departure", input.get("depmonth"), input.get("depdate"));
		tripDetailsPage.selectDate("Return", input.get("returnmonth"), input.get("returndate"));
		tripDetailsPage.clickNextButton();
		
		CoverSelectionPage coverSelectionPage = new  CoverSelectionPage(driver);
		coverSelectionPage.selectCoverType(input.get("coverSelect"));
		coverSelectionPage.clickNextButton();
		
		//Customer Information Verification
		Assert.assertEquals(informationPage.getFirstName(), input.get("firstName"));
		Assert.assertEquals(informationPage.getLastName(), input.get("lastName"));
		Assert.assertEquals(informationPage.getEmail(), input.get("email"));
		Assert.assertEquals(informationPage.getPhoneNumber(), input.get("phoneNumber"));
		Assert.assertEquals(tripDetailsPage.getResident(), input.get("resident"));
		
		//Trip Details Verification
		Assert.assertEquals(tripDetailsPage.getDestination(), input.get("country"));
		Assert.assertEquals(tripDetailsPage.getDate("Departure"), input.get("depdate"));
		Assert.assertEquals(tripDetailsPage.getMonth("Departure"), input.get("depmonthNumber"));
		Assert.assertEquals(tripDetailsPage.getDate("Return"), input.get("returndate"));
		Assert.assertEquals(tripDetailsPage.getMonth("Return"), input.get("returnmonthNumber"));
		Assert.assertEquals(tripDetailsPage.getCoverType(), input.get("coverType"));
		Assert.assertEquals(tripDetailsPage.getCoverageOption(), input.get("coverageOption"));
		Assert.assertEquals(tripDetailsPage.getTravellerCount(input.get("travellerType")), input.get("travellerNumber"));
		
		
		coverSelectionPage.addPassport();
		Runtime.getRuntime().exec(input.get("passportPath"));
		Thread.sleep(5000);
		coverSelectionPage.selectConfirmationCheckbox();
		Thread.sleep(3000);
		coverSelectionPage.selectDob(input.get("dobYear"), input.get("dobMonth"), input.get("dobDate"));
		coverSelectionPage.addFirstName(input.get("firstName"));
		coverSelectionPage.addLastName(input.get("lastName"));
		coverSelectionPage.addNationality(input.get("country"));
		coverSelectionPage.addPassportNo(input.get("passportNo"));
		coverSelectionPage.clickNextButton();
		
		PaymentPage paymentPage = new PaymentPage(driver);
		paymentPage.paymentSectionExpand();
		Thread.sleep(3000);
		driver.switchTo().frame("paymentIFrame");
		paymentPage.enterCreditCardNumber(input.get("creditCardNumber"));
		paymentPage.enterExpiryMonth(input.get("expiryMonth"));
		paymentPage.enterExpiryYear(input.get("expiryYear"));
		paymentPage.enterCvv(input.get("cvvNumber"));
		paymentPage.makePayment();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(6));
		driver.switchTo().defaultContent();
		
		Assert.assertEquals(driver.getTitle(), "Verified by Visa");
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Mindpool//Data//SubmitInsurance.json");
		return new Object[][]  {{data.get(0)} };
		
	}
}
