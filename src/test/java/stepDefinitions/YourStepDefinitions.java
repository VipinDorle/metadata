package stepDefinitions;

import pages.QueuePage;
import pages.MetadataPage;
import utils.ExcelReader;
import utils.LoggerUtil;
import utils.ScenarioContext;

import java.util.Map;

import io.cucumber.java.en.*;

public class YourStepDefinitions {

    QueuePage queuePage = new QueuePage();
    MetadataPage metadataPage = new MetadataPage();

    @Given("I navigate to queue {string}")
    public void iNavigateToQueue(String queueName) {
        queuePage.navigateToQueue(queueName);
        LoggerUtil.logInfo("Navigated to queue: " + queueName);
    }

    @Given("I select the document at index {int}")
    public void iSelectTheDocument(int index) {
        queuePage.selectDocument(index);
        LoggerUtil.logInfo("Selected document at index: " + index);
    }

    @When("I assign metadata field {string} with value {string}")
    public void iAssignMetadataField(String fieldName, String value) {
        metadataPage.assignMetadataField(fieldName, value);
        LoggerUtil.logPass("Assigned " + fieldName + " with value: " + value);
    }

    @When("I save the metadata")
    public void iSaveTheMetadata() {
        metadataPage.saveMetadata();
        LoggerUtil.logPass("Metadata saved successfully.");
    }
    @When("I assign metadata for {string}")
    public void i_assign_metadata_for_testcaseid(String testCaseId) {
        ScenarioContext.getTest().info("Starting test for TestCaseID: " + testCaseId);

        Map<String, String> metadata = ExcelReader.getMetadataForTestCase(testCaseId);

        if (testCaseId.toLowerCase().startsWith("tcvalid")) {
            ScenarioContext.getTest().info("Running Happy Path Test for " + testCaseId);
            assignAllMetadataFields(metadata);
        } else if (testCaseId.toLowerCase().startsWith("tcinvalid")) {
            ScenarioContext.getTest().info("Running Validation Test for " + testCaseId);
            assignInvalidMetadataField(metadata);
        } else {
            ScenarioContext.getTest().warning("Unknown TestCaseID format: " + testCaseId);
        }
    }
    public void assignAllMetadataFields(Map<String, String> metadata) {
        // assign only non-empty fields
        for (Map.Entry<String, String> entry : metadata.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                MetadataPage.assignField(entry.getKey(), entry.getValue());
            }
        }
    }

    public void assignInvalidMetadataField(Map<String, String> metadata) {
        // assumption: in invalid test case, only ONE field will have invalid data, others will be blank
        for (Map.Entry<String, String> entry : metadata.entrySet()) {
            if (!entry.getValue().isEmpty()) {
            	MetadataPage.assignField(entry.getKey(), entry.getValue());
                break; // Only assign the first invalid field found
            }
        }
    }


}
