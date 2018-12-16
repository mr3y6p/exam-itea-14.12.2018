package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject class for home page
 */
public class HomePage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchField;
    @FindBy(xpath = "//div[@class='FPdoLc VlcLAe']/center/input[@name='btnK']")
    private WebElement searchButton;


    /**
     * Constructor of LoginPage class.
     * @param webDriver - webDriver instance from Test.
     */
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitUntilElementIsVisible(searchField);
    }


    /**
     * Method to check if page is loaded
     * @return true/false
     */
    public boolean isPageLoaded() {
        return searchField.isDisplayed()
                && webDriver.getTitle().contains("Google")
                && webDriver.getCurrentUrl().equals("https://www.google.com.ua/");
    }

    /**
     * Method to search some info in page by searchTerm parameter
     * @param searchTerm - parameter for searching
     * @return SearchResultsPage object.
     */
    public SearchResultsPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchButton.click();

        return new SearchResultsPage(webDriver);
    }
}