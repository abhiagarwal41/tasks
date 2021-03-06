package pages;


import org.openqa.selenium.By;
import utils.NuSuchPageException;


public abstract class AbstractPage {

    private static AbstractPage testPage;

    public static AbstractPage getTestPage() {
        return testPage;
    }

    public abstract By getElementLocator(String name);

    public static void setTestPage(String pageName) throws NuSuchPageException {

        switch (pageName.toLowerCase()) {
            case "home_page":
                testPage = new HomePage();
                break;
            case "heatmap_page":
                testPage = new HeatmapPage();
                break;
            default:
                throw new NuSuchPageException("No such page exists: " + pageName);
        }
    }

}
