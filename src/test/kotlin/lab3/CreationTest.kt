package lab3

import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.random.Random.Default.nextInt


class CreationTest {
    private val baseUrl = "https://www.blogspot.com"

    companion object Driver {
        val driver: WebDriver = ChromeDriver().apply {
            manage().timeouts()?.implicitlyWait(3, TimeUnit.SECONDS)
            manage().window()?.maximize()
        }

         @AfterAll
         @JvmStatic
         fun closeDriver() {
             driver.quit()
         }
    }

    private fun isLoginNeeded(): Boolean {
        return driver.findElements(By.xpath("//a[contains(@class, 'sign-in')]")).isNotEmpty()
    }

    private fun login() {
        driver.run {
            clickByXpath("//a[contains(@class, 'sign-in')]")
            typeByXpath("//input[@type='email']", "faketestse@gmail.com")
            clickByXpath("//button[span='Далее']")
            WebDriverWait(
                this,
                Duration.ofSeconds(3).toSeconds()
            ).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")))
                .sendKeys("faketestsePass3,")
            clickByXpath("//button[span='Далее']")
            if (isLoginNeeded()) {
                waitForAndClick("//a[contains(@class, 'sign-in')]")
                clickByXpath("//div[@data-identifier='faketestse@gmail.com']")
            }
        }
    }

    private fun createInitialBlog() {
        driver.run {
            findElement(By.xpath("//input[@aria-label='Название']")).sendKeys("Tpo3")
            findElement(By.xpath("//div[@role='button'][span='Далее']")).click()
            findElement(By.xpath("//input[@aria-label='Адрес']")).sendKeys("seTpo" + nextInt(50))
            WebDriverWait(driver, Duration.ofSeconds(3).toSeconds()).until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[.='Этот адрес блога не занят.']")
                )
            )
            WebDriverWait(
                driver,
                Duration.ofSeconds(3).toSeconds()
            ).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button']/span[span='Далее']")))
                .click()
            findElement(By.xpath("//div[@role='button']/span[span='Далее']")).click()
            findElement(By.xpath("//input[@aria-label='Отображаемое имя']")).sendKeys("Tpo3")
            WebDriverWait(
                driver,
                Duration.ofSeconds(3).toSeconds()
            ).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button']/span[span='Готово']")))
                .click()
        }
    }

    @BeforeEach
    fun openBasePage() {
        driver.navigate().to(baseUrl)
        if (isLoginNeeded()) login()
    }

    @Test
    fun createNewBlog() {
        driver.run {
            val randomNum = nextInt(100)
            findElement(By.xpath("//div[@role='listbox'][@aria-label='Blog Selection']")).apply {
                findElement(By.xpath("./div[@role='presentation']")).click()
                findElement(By.xpath("./div[last()]/div[last()]")).click()
            }
            waitForAndType("//input[@aria-label='Название'][@aria-invalid]", "New blog $randomNum")
            clickByXpath("//div[@role='button']/span[span='Далее']")
            typeByXpath("//input[@aria-label='Адрес']", "seTpo$randomNum")
            WebDriverWait(this, Duration.ofSeconds(3).toSeconds()).until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[.='Этот адрес блога не занят.']")
                )
            )
            waitForAndClick("//div[@role='button']/span[span='Сохранить']")
            Assertions.assertTrue(findElements(By.xpath("//div[@aria-label='New blog $randomNum']")).isNotEmpty())
        }
    }

    @Test
    fun addNewPost() {
        driver.run {
            clickByXpath("//div[@role='button'][@aria-label='Создать новое сообщение']")
            findElement(By.xpath("//input[@aria-label='Название']")).apply {
                click()
                sendKeys("New post")
            }
            switchTo().frame(findElement(By.xpath("//iframe[contains(@class, 'editable')]")))
            typeByXpath("//body", "Post body")
            switchTo().defaultContent()
            clickByXpath("//span[@title='Изменения сохранены.']")
            waitForAndClick(
                "//div[@role='button'][@aria-label='Опубликовать'][@data-tooltip='Опубликовать']/*//div[.='Опубликовать']",
                5
            )
            waitForAndClick("//span[.='При этом сообщение будет опубликовано в вашем блоге.']/following-sibling::div/div[@role='button']/span/span[.='ОК']")
            Assertions.assertTrue(findElements(By.xpath("//div[@role='listitem']//span[.='New post']")).isNotEmpty())
        }
    }
}