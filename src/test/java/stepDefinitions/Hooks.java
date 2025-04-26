package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.*;
import utils.ExtentManager;
import utils.LoggerUtil;
import utils.ScenarioContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Hooks {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        BaseTest.initializeDriver();
        test = extent.createTest(scenario.getName());
        ScenarioContext.setTest(test);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerUtil.logFail("Scenario Failed: " + scenario.getName());
        } else {
            LoggerUtil.logPass("Scenario Passed: " + scenario.getName());
        }
        BaseTest.quitDriver();
        extent.flush();
    }
}
