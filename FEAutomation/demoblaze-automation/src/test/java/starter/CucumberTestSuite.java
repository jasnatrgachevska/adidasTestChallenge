package starter;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.io.IOException;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"src/test/resources/features/purchase.feature"},
        glue = {"starter/stepdefinitions"}
)
public class CucumberTestSuite {

    private CucumberTestSuite() {
    }

    @BeforeClass
    public static void setup() throws IOException {
        /*
        Here goes code for setting up the tests
         */
    }

}
