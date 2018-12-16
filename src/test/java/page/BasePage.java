package page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * PageObject class for base abstract page
 */
public abstract class BasePage {
    /**
     * Class which describes browser opening
     */
    protected WebDriver webDriver;

    /**
     * Class which describe waiting element on page
     * @param elementToWaitFor - Web element for waiting
     */
    protected void waitUntilElementIsVisible(WebElement elementToWaitFor) {
        waitUntilElementIsVisible(elementToWaitFor, 5);
    }

    /**
     * Class which describe waiting element on page
     * @param elementToWaitFor - Web element for waiting
     * @param timeOutInSeconds - Max time for waiting in seconds
     */
    protected void waitUntilElementIsVisible(WebElement elementToWaitFor, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
    }

    /**
     * It is required class for each Page class
     * @return true/false
     */
    public abstract boolean isPageLoaded();

}
