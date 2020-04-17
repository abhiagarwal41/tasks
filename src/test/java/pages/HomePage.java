package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class HomePage extends AbstractPage {


    @Override
    public By getElementLocator(String name) {
        switch (name.toLowerCase()) {
            case "view_heatmap":
                return By.xpath("//div[@class='heatmap-thumb__default-heatmap']");
            default:
                throw new NoSuchElementException(name);
        }
    }
}
