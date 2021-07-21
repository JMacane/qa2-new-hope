package pageobject.tickets.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

import java.util.List;

public class PassengerInfoPage {
    private final By AIRPORT_NAME = By.xpath(".//span[@class = 'bTxt']");
    private BaseFunc baseFunc;

    public PassengerInfoPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public List<WebElement> getAirports(){
        return baseFunc.findElements(AIRPORT_NAME);
    }

}
