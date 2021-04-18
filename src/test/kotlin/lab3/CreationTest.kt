package lab3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.random.Random.Default.nextInt


class CreationTest {
    private val driver: WebDriver = ChromeDriver().apply {
        manage().timeouts()?.implicitlyWait(3, TimeUnit.SECONDS)
        manage().window()?.maximize()
    }
    private val baseUrl = "https://www.blogspot.com"

    private fun isLoginNeeded(): Boolean {
        return driver.findElements(By.xpath("//a[contains(@class, 'sign-in')]")).isNotEmpty()
    }

    private fun login() {
        driver.run {
            clickByXpath("//a[contains(@class, 'sign-in')]")
            typeByXpath("//input[@type='email']", "faketestse@gmail.com")
            clickByXpath("//button[span='Далее']")
            WebDriverWait(
                driver,
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
        driver.get(baseUrl)
        if (isLoginNeeded()) login()
    }

    private fun addNewBlog() {
        driver.run {
            val randomNum = nextInt(50)
            get(baseUrl)
            findElement(By.xpath("//div[@role='listbox'][@aria-label='Blog Selection']")).apply {
                clickByXpath("./div[@role='presentation']")
                clickByXpath("./div[last()]/div[last()]")
            }
            typeByXpath("//input[@aria-label='Название']", "New blog $randomNum")
            clickByXpath("//div[@role='button']/span[span='Далее']")
            typeByXpath("//input[@aria-label='Адрес']", "seTpo$randomNum")
            WebDriverWait(this, Duration.ofSeconds(3).toSeconds()).until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[.='Этот адрес блога не занят.']")
                )
            )
            waitForAndClick("//div[@role='button']/span[span='Сохранить']")
            Assertions.assertTrue(findElements(By.xpath("//div[@aria-label='Tpo$randomNum']")).isNotEmpty())
        }
    }

    @Test
    fun createNewBlog() {
        addNewBlog()
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
            waitForAndClick("//div[@role='button'][@aria-label='Опубликовать'][@data-tooltip='Опубликовать']/*//div[.='Опубликовать']", 5)
            waitForAndClick("//span[.='При этом сообщение будет опубликовано в вашем блоге.']/following-sibling::div/div[@role='button']/span/span[.='ОК']")
            Assertions.assertTrue(findElements(By.xpath("//div[@role='listitem']//span[.='New post']")).isNotEmpty())
        }
    }
}