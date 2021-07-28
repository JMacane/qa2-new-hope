package pageobject.tickets.pages;

import model.Reservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

import java.util.List;

public class PassengerInfoPage {
    private final By AIRPORT_NAME = By.xpath(".//span[@class = 'bTxt']");

    private final By NAME = By.id("name");
    private final By SURNAME = By.id("surname");
    private final By DISCOUNT = By.id("discount");
    private final By ADULTS = By.id("adults");
    private final By CHILDREN = By.id("children");
    private final By LUGGAGE = By.id("bugs");
    private final By FLIGHT = By.id("flight");

    private BaseFunc baseFunc;

    public PassengerInfoPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public List<WebElement> getAirports(){
        return baseFunc.findElements(AIRPORT_NAME);
    }

    public void submitPassengerInfo (Reservation reservation) {

    }

}
