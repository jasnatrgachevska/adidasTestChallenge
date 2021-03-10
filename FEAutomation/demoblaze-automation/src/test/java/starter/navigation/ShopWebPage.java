package starter.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.demoblaze.com/index.html")
public
class ShopWebPage extends PageObject {
    public static Target AMOUNT = Target.the("Amount")
            .locatedBy("//*[@id=\"totalp\"]");

    public static Target FINAL_AMOUNT = Target.the("Payed amount")
            .locatedBy("/html/body/div[10]/p/text()[contains(.,'Amount:')]/..");
}
