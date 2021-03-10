package starter.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.Wait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Actions {

    public static Performable clickText(String linkText) {
        return Task.where("{0} navigates to '" + linkText + "'",
                Click.on(By.xpath("//*[text()='"+ linkText +"']")).afterWaitingUntilEnabled());
    }

    public static void acceptPopup(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        new WebDriverWait(driver, 10)
                .ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public static Performable deleteItem(String item) {
        return Task.where("{0} deletes to '" + item + "'",
                Click.on(By.xpath("//*[@id=\"tbodyid\"]/tr/td[text()='"+item+"']/../td/a[text()='Delete']")).afterWaitingUntilEnabled());


    }

    public static void fillPlaceOrderWebForm(Actor actor){
        actor.attemptsTo(Wait.until(
                WebElementQuestion.the(By.xpath("//*[@id=\"orderModal\"]/div/div")) , WebElementStateMatchers.isClickable()
        ).forNoLongerThan(10).seconds());
        actor.attemptsTo(fillFieldById("name", "Maximilian"));
        actor.attemptsTo(fillFieldById("country", "Spain"));
        actor.attemptsTo(fillFieldById("city","Palma"));
        actor.attemptsTo(fillFieldById("card","1111"));
        actor.attemptsTo(fillFieldById("month", "02"));
        actor.attemptsTo(fillFieldById("year", "2021"));
    }
    public static Performable fillFieldById(String id, String value) {
        return Task.where("{0} fills '" + id + "' with value '" + value + "'",
                Enter.theValue(value)
                        .into(Target.the(id)
                                .locatedBy("//*[@id=\""+id+"\"]"))
                        .thenHit(Keys.TAB));
    }

    public static Performable clickButton(By elementLocation) {
        return Task.where("{0} clicks button with " + elementLocation + "'",
                Click.on(elementLocation).afterWaitingUntilEnabled());
    }

    public static void waitAndClickByCssClass(Actor actor, String cssClass) {
        actor.attemptsTo(Wait.until(
                WebElementQuestion.the(By.className(cssClass)) , WebElementStateMatchers.isClickable()
        ).forNoLongerThan(10).seconds());
        actor.attemptsTo(Actions.clickButton(By.className(cssClass)));
    }

    public static void waitAndClickByText(Actor actor, String text) {
        actor.attemptsTo(Wait.until(
                WebElementQuestion.the(By.xpath("//*[text()='"+ text +"']")) , WebElementStateMatchers.isClickable()
        ).forNoLongerThan(10).seconds());
        actor.attemptsTo(Actions.clickText(text));
    }


    public static String valueExtraction(String label, String value) {
        final Pattern amountPattern = Pattern.compile(String.format("%s: ([0-9]+)(.*)",label));

        Matcher m = amountPattern.matcher(value);
        if (m.find()){
            return m.group(0).replaceAll("\\D+","");
        }
        return null;
    }
}
