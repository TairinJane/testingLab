package lab3

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.concurrent.TimeUnit

open class BaseTest(private val baseUrl: String = "https://www.blogspot.com") {
    companion object Driver {

        init {
            WebDriverManager.chromedriver().setup()
            WebDriverManager.firefoxdriver().setup()
        }

        var driver: WebDriver = FirefoxDriver().apply {
            manage().timeouts()?.implicitlyWait(3, TimeUnit.SECONDS)
            manage().window()?.maximize()
        }

        /*@AfterAll
        @JvmStatic
        fun closeDriver() {
            driver.quit()
        }*/
    }

    @BeforeEach
    fun openBasePage() {
        driver.navigate(baseUrl)
        if (isLoginNeeded()) login()
    }

    private fun isLoginNeeded(): Boolean {
        return driver.findElements(By.xpath("//a[contains(@class, 'sign-in')]")).isNotEmpty()
    }

    fun fillLoginFormIfNeeded() {
        driver.run {
            if (findElements(By.xpath("//input[@type='email']")).isEmpty()) return
            typeByXpath("//input[@type='email']", "faketestse@gmail.com")
            clickByXpath("//button[span='Далее']")
            WebDriverWait(
                this,
                Duration.ofSeconds(3).toSeconds()
            ).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")))
                .sendKeys("faketestsePass3,")
            clickByXpath("//button[span='Далее']")
        }
    }

    private fun login() {
        driver.run {
            clickByXpath("//a[contains(@class, 'sign-in')]")
            fillLoginFormIfNeeded()
            if (isLoginNeeded()) {
                waitForAndClick("//a[contains(@class, 'sign-in')]")
                clickByXpath("//div[@data-identifier='faketestse@gmail.com']")
            }
        }
    }
}