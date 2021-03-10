package com.petstore.serenityRunner;

import cucumber.api.CucumberOptions;
import io.restassured.RestAssured;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Main class with the execution settings and @BeforeClass hook
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/petStore.feature"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        glue = {"com.petstore.gherkinsDefinitions"}
)
public class TestRunner {

    private TestRunner() {
    }

    /**
     * This is a setup method which is executed before gherkin scenarios.
     * In this method are set necessary for execution values.
     */
    @BeforeClass
    public static void setup() throws IOException {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/resources/data.properties"));

        String api_key = prop.getProperty("api_key");
        System.setProperty("api_key", api_key);
    }
}
