package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            TITLE_LOCATOR = "//*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@resource-id='org.wikipedia:id/view_page_title_text']",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list'][@instance='2']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SAVE_TO_EXISTING_READING_LIST_BUTTON_TPL = "//*[@text='{READING_LIST_NAME}']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Can't find article title", 10);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter ()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT ), "Can't find the end of article", 20);
    }
    public void addArticleToMyList(String name_of_folder)
    {
        /*this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),
                "Can't find or click Options button", 8);
РАБОТАЮЩИЙ КОД
         */
        this.tapOptionsElementLowerPoint(By.xpath(OPTIONS_BUTTON),
                "Can't find or tap options button by coordinates",
                10); //мой метод, который тапает по краю кнопки Options - пытался решить проблему с статусбаром
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                //Так тапаем по 3 пункту выпадающего меню (индекс), имеющему инстанс 2. без //* между параметрами, так как они находятся
                //в самом linearLayout (By.xpath("//*[@class='android.widget.LinearLayout'][@index='2'][@instance='2']")
                "Can't find or click 'Add to reading list' button", 8);
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY),
                "Can't find 'got it' tip overlay", 8);
        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT),
                "Can't find text input or clear it", 8);
        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT),
                name_of_folder, "Can't find text input or type the name",
                8);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),
                "Can't find or click 'OK' button", 8);
    }

    public void addMoreArticlesToMyList(String name_of_folder)
    {
        this.tapOptionsElementLowerPoint(By.xpath(OPTIONS_BUTTON),
                "Can't find or tap options button by coordinates",
                10); //мой метод, который тапает по краю кнопки Options - пытался решить проблему с статусбаром
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can't find or click 'Add to reading list' button", 8);
        this.waitForSavedListButtonAndClick(name_of_folder);
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),
                "Can't find or click close button", 8);
    }
    private static String getExistingReadingListXpathByName (String name_of_folder) {
        return SAVE_TO_EXISTING_READING_LIST_BUTTON_TPL.replace("{READING_LIST_NAME}",name_of_folder);
    }
    public void waitForSavedListButtonAndClick(String name_of_folder) {
        String my_list_folder_name = getExistingReadingListXpathByName(name_of_folder);
        this.waitForElementAndClick(By.xpath(my_list_folder_name), "Can't find or click on the saved folder",10);
    }
    public void assertArticleTitlePresentedByName (){
        this.assertElementPresent(By.xpath(TITLE_LOCATOR),"title element aren't presented");
    }
}
