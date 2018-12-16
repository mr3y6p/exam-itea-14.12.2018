package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Optional;

import java.util.ArrayList;
import java.util.List;

/**
 * PageObject class for search result page
 */
public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//div[@id='hdtb']")
    private WebElement searchFilterBar;
    @FindBy(xpath = "//div[@class='srg']/div[@class='g']")
    private List<WebElement> searchResults;
    @FindBy (xpath = "//a[@aria-label='Page 2']")
    private WebElement pageLink2;
    @FindBy (xpath = "//td[@class='cur']")
    private WebElement currentPage;

    /**
     * Constructor of SearchResultsPage class.
     * @param webDriver - webDriver instance from Test.
     */
    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitUntilElementIsVisible(searchFilterBar);
    }

    /**
     * Method to check if page is loaded
     * @return true/false
     */
    public boolean isPageLoaded() {
        return searchFilterBar.isDisplayed()
                && webDriver.getTitle().contains("Google")
                && webDriver.getCurrentUrl().contains("https://www.google.com.ua/search");
    }

    /**
     * Method which count number of elements
     * @return - number of elements
     */
    public int getSearchResultsCount() {
        return searchResults.size();
    }

    /**
     * Method which create List of search result elements
     * @return List of elements
     */
    public List<String> getSearchResults() {
        List<String> searchResultsList = new ArrayList<String>();
        for (WebElement searchResult : searchResults) {
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", searchResult);
            String searchResultText = searchResult.getText();
            searchResultsList.add(searchResultText);
        }
        return searchResultsList;
    }

    /**
     * Method for opening Page 2
     */
    public void getPage2() {
        pageLink2.click();
    }


    /**
     * Method which check pageNumber
     * @return Number of page in String format
     */
    public String getCurrentPage() {
        return currentPage.getText();
    }



}
