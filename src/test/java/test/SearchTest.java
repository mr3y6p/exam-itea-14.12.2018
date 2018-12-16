package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchResultsPage;

import java.util.List;

public class SearchTest extends BaseTest {

    /**
     * Preconditions:
     * - Open browser
     * - Navigate to google.com.ua
     *
     * Scenario:
     * - Verify Home page is loaded
     * - Enter "selenium" into searchField
     * - Click searchButton
     * - Verify SearchResult page is loaded
     * - Verify results list contains 10 items
     * - Verify each item contains searchTerm
     * - Click on 2 link to switch to next page
     * - Verify results list contains 10 items
     * - Verify each item contains searchTerm
     *
     * Postcondition:
     * - Close browser
     */
    @Test(dataProvider = "keySensitiveTerms")
    public void basicSearchTest(String searchTerm) {

        SearchResultsPage searchResultsPage = homePage.search(searchTerm);
        Assert.assertTrue(searchResultsPage.isPageLoaded(), "SearchResult page is not loaded");

        Assert.assertEquals(searchResultsPage.getSearchResultsCount(), 9, "Search results count is wrong");

        List<String> searchResultsPage1 = searchResultsPage.getSearchResults();
        for (String searchResult : searchResultsPage1){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()), "SearchTerm " + searchTerm + " not found in: \n" + searchResult + " :Page1");
        }

        searchResultsPage.getPage2();
        Assert.assertEquals(searchResultsPage.getCurrentPage(), "2", "Current page is not 2");

        Assert.assertEquals(searchResultsPage.getSearchResultsCount(), 10, "Search results count is wrong");

        List<String> searchResultsPage2 = searchResultsPage.getSearchResults();
        for (String searchResult : searchResultsPage2){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()), "SearchTerm " + searchTerm + " not found in: \n" + searchResult + " :Page2");
        }


    }

    @DataProvider
    public Object[][] keySensitiveTerms() {
        return new Object[][]{
                {"selenium"},
                {"Selenium"},
                {"SeLeNiUm"}
        };
    }
}
