package pageobject.tickets.pages;
import org.junit.jupiter.api.Assertions;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.openqa.selenium.By;
import pageobject.BaseFunc;

public class SuccessPage {
    private final By SUCCESS_TXT = By.xpath(".//div[@class = 'finalTxt']");
    private BaseFunc baseFunc;

    public SuccessPage(BaseFunc baseFunc)  {
        this.baseFunc = baseFunc;
    }

    public String getMessage () {
        return baseFunc.getText(SUCCESS_TXT);
    }
}
