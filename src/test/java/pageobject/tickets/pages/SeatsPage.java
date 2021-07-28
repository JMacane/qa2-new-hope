package pageobject.tickets.pages;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

public class SeatsPage {
    private final By BOOK_BTN = By.id("book3");
    private final By SEAT = By.xpath(".//div[@class = 'seat']");
    private final By SEAT_NR = By.xpath(".//div[@class = 'line']");

    private BaseFunc baseFunc;

    public SeatsPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectSeat(int nr) {
        WebElement seat= findSeat(nr);
        Assertions.assertNotNull(seat, "Cannot find Seat with Nr." + nr);
        baseFunc.click(seat);
    }

    private WebElement findSeat (int nr) {
        for (WebElement we : baseFunc.findElements(SEAT)) {
            if (Integer.parseInt(we.getText()) == nr){
                return we;
            }
        }
        return null;
    }

    public int getSeatNr () {
        return Integer.parseInt(StringUtils.substringAfterLast(baseFunc.getText(SEAT_NR), "is: "));
    }

    public SuccessPage book () {
        baseFunc.click(BOOK_BTN);
        return new SuccessPage(baseFunc);
    }
}

