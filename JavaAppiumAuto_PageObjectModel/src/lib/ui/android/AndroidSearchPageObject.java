package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_RESULT_WITH_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_RESULT_ELEMENT_BY_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_RESULT_ELEMENT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[./*[@text='{ARTICLE_TITLE}'] and ./*[@text='{ARTICLE_DESCRIPTION}']]";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_EMPTY_MESSAGE = "id:org.wikipedia:id/search_empty_message";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
