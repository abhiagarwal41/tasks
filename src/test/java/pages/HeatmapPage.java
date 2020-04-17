package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class HeatmapPage extends AbstractPage {


    @Override
    public By getElementLocator(String name) {
        switch (name.toLowerCase()) {
            case "element_list":
                return By.xpath("//span[contains(text(),'Element List')]");
            case "heatmap_iframe":
                return By.id("heatmap-iframe");
            case "element_list_iframe":
                return By.id("element-list-iframe");
            case "element_list_panel":
                return By.id("element-list");
            case "start_free_trial_element":
                return By.xpath("//td[contains(text(),'Start Free trial')]");
            case "start_free_trial_button":
                return By.xpath("//button[contains(text(),'Start Free trial')]");
            case "start_free_trial_button_border":
                return By.id("_vwo_glass");
            default:
                throw new NoSuchElementException(name);
        }
    }
}
