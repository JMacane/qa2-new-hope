package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Reservation;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;
import pageobject.tickets.pages.HomePage;
import pageobject.tickets.pages.PassengerInfoPage;

import java.util.List;
import java.util.Map;

public class TicketsStepDefs {
    private Reservation given = new Reservation();
    private BaseFunc baseFunc;
    private HomePage homePage;
    private PassengerInfoPage infoPage;

    @Given("flight from {string} to {string}")
    public void set_airports(String from, String to) {
        given.setAfrom(from);
        given.setAto(to);
    }

    @Given("passenger info:")
    public void set_info(Map<String, String> info) {
        given.setName(info.get("name"));
        given.setSurname(info.get("surname"));
        given.setDiscount(info.get("discount"));
        given.setAdults(Integer.parseInt(info.get("passenger count")));
        given.setChildren(Integer.parseInt(info.get("child count")));
        given.setBugs(Integer.parseInt(info.get("luggage")));
        given.setFullDate(info.get("date"));
    }

    @Given("seat number is: {int}")
    public void set_seat_nr(int nr) {
        given.setSeat(nr);
    }

    @When("we are opening home page")
    public void open_home_page() {
        baseFunc = new BaseFunc();
        baseFunc.openPage("qaguru.lv:8089/tickets");
        homePage = new HomePage(baseFunc);
    }

    @When("selecting airports")
    public void select_airports() {
        infoPage = homePage.selectAirports(given.getAfrom(), given.getAto());

    }

    @Then("airports are displayed on passenger info page")
    public void check_airports() {
        List<WebElement> airports = infoPage.getAirports();

        Assertions.assertEquals(given.getAfrom(), airports.get(0).getText(), "Wrong departure airport");
        Assertions.assertEquals(given.getAto(), airports.get(1).getText(), "Wrong arrival airport");

    }
}
