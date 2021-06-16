package stepdefs;

import io.cucumber.java.en.Given;

public class HelloStepDefs {
    @Given("say hello to world")
    public void say_hello() {
        System.out.println("Hello, World!!! ");
    }

    @Given("say {string} also")
    public void print_custom_message(String message) {
        System.out.println(message);
    }
}
