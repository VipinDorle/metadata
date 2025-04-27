package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class MetadataSteps {
	@Given("I navigate to {string}")
    public void i_navigate_to_queue(String queueName) {
        System.out.println("Navigating to queue: " + queueName);
        // Your WinAppDriver navigation logic
    }

    @When("I select the document")
    public void i_select_the_document() {
        System.out.println("Selecting the document.");
        // Your selection logic
    }

    @Then("I assign metadata to the document")
    public void i_assign_metadata_to_the_document() {
        System.out.println("Assigning metadata.");
        // Metadata assignment logic
    }
}
