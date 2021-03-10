package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;
import org.openqa.selenium.By;
import starter.navigation.NavigateTo;
import starter.actions.Actions;
import starter.navigation.ShopWebPage;

public class SearchStepDefinitions {
    public ShopWebPage webPage;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{actor} navigates to DemoblazePage")
    public void theUserNavigatesToDemoblazePage(Actor actor) {
        actor.wasAbleTo(NavigateTo.theShopWebPage());

    }

    @And("{actor} navigates to category {string} and item {string}")
    public void navigatesToCategoryAndItem(Actor actor, String category, String item) {
        Actions.waitAndClickByText(actor,category);
        Actions.waitAndClickByText(actor,item);

    }

    @And("{actor} adds {string} to cart by clicking {string}")
    public void addsItemToCart(Actor actor, String item, String addToCardButtonText) {
        actor.attemptsTo(Actions.clickText(addToCardButtonText));
    }

    @And("{actor} accepts pop up confirmation")
    public void acceptsPopUpConfirmation(Actor actor) {

        Actions.acceptPopup(actor);

    }

    @And("{actor} navigates to {string}")
    public void sheNavigatesTo(Actor actor, String cart) {
        Actions.waitAndClickByText(actor,cart);
    }

    @And("{actor} deletes {string} from cart")
    public void deletesFromCart(Actor actor, String item) {
        actor.attemptsTo(Wait.until(
                WebElementQuestion.the(By.xpath("//*[@id=\"tbodyid\"]/tr/td[text()='"+item+"']/../td/a[text()='Delete']")) , WebElementStateMatchers.isClickable())
                .forNoLongerThan(10).seconds());
        actor.attemptsTo(Actions.deleteItem(item));
        actor.attemptsTo(Wait.until(
                WebElementQuestion.the(By.xpath("//*[@id=\"tbodyid\"]/tr/td[text()='"+item+"']/../td/a[text()='Delete']")) , WebElementStateMatchers.isNotPresent())
                .forNoLongerThan(4).seconds());
    }

    @And("{actor} places order and remembers the amount")
    public void shePlacesOrderandremmembertheamount(Actor actor) {
        actor.remember("Amount", Text.of(webPage.AMOUNT).viewedBy(actor).asString());
        Actions.waitAndClickByCssClass(actor,"btn-success");
    }

    @And("{actor} fills in all web form fields")
    public void heFillsInAllWebFormFields(Actor actor) {
        Actions.fillPlaceOrderWebForm(actor);
    }

    @And("{actor} clicks on {string}")
    public void clicksOn(Actor actor, String text) {
        Actions.waitAndClickByText(actor,text);
    }

    @And("{actor} captures and log {string} and {string}")
    public void sheCapturesAndLogPurchaseIDAndAmount(Actor actor, String purchaseId, String amount) {
        Serenity.takeScreenshot();
        amount = Actions.valueExtraction("Amount", webPage.FINAL_AMOUNT.resolveFor(actor).getTextValue());
        purchaseId = Actions.valueExtraction("Id", webPage.FINAL_AMOUNT.resolveFor(actor).getTextValue());
        actor.remember("PurchaseId", purchaseId);
        actor.remember("FinalAmount", amount);
    }

    @Then("{actor} asserts purchase amount equals expected")
    public void assertsPurchaseAmountEqualsExpected(Actor actor) {
        actor.attemptsTo(Ensure.that(actor.recall("Amount").toString()).isEqualTo(actor.recall("FinalAmount").toString()));
    }
}
