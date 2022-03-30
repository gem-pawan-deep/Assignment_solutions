package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition {
    @Given("User is on NetBanking landing page")
    public void User_is_on_NetBanking_landing_page()
    {
        System.out.println("Net Banking landing Page");
    }
    @When("User login into application with username and password")
    public void User_login_into_application_with_username_and_password()
    {
        System.out.println("Login with Username and Password");
    }
    @Then("Home page is populated")
    public void Home_page_is_populated()
    {
        System.out.println("Populated Home page");
    }
    @And("Cards are displayed")
    public void Cards_are_displayed() {
        System.out.println("Display cards");
    }
    @When("User login into application with {string} and password {string}")
    public void User_login_into_application_with_and_password(String username,String password)
    {
        System.out.println(username + " -"+ password);
    }
    @And("Cards displayed are {string}")
    public void Cards_displayed_are(String card)
    {
        System.out.println(card);
    }
    @When("^User login in to application with (.+) and password (.+)$")
    public void User_login_in_to_application_with_and_password(String name,String password)
    {
        System.out.println(name+ " "+ password);
    }
}
