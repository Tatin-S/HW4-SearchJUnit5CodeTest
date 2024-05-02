import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchJUnit5CodeTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void searchJUnit5CodeTest() {
        open("/selenide/selenide");

        //Open Wiki
        $("#wiki-tab").click();

        //Open SoftAssertions
        $("#wiki-pages-filter").click();
        $("#wiki-pages-filter").sendKeys("SoftAssertions");
        $("#wiki-pages-box").$(byText("SoftAssertions")).shouldBe(visible);
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();

        //Check JUnit5 code
        $("#wiki-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """));
    }
}
