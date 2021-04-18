package lab3

import org.junit.jupiter.api.Assertions
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
            findElement(By.xpath("//a[contains(@class, 'sign-in')]")).click()
            findElement(By.xpath("//input[@type='email']")).sendKeys("faketestse@gmail.com")
            findElement(By.xpath("//button[span='Далее']")).click()
            WebDriverWait(
                driver,
                Duration.ofSeconds(3).toSeconds()
            ).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")))
                .sendKeys("faketestsePass3,")
            findElement(By.xpath("//button[span='Далее']")).click()
            if (isLoginNeeded()) {
                WebDriverWait(
                    driver,
                    Duration.ofSeconds(3).toSeconds()
                ).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'sign-in')]"))).click()
                findElement(By.xpath("//div[@data-identifier='faketestse@gmail.com']")).click()
            }
        }
    }

    fun createInitialBlog() {
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

    private fun addNewBlog() {
        driver.run {
            val randomNum = nextInt(50)
            get(baseUrl)
            findElement(By.xpath("//div[@role='listbox'][@aria-label='Blog Selection']")).apply{
                findElement(By.xpath("./div[@role='presentation']")).click()
                findElement(By.xpath("./div[last()]/div[last()]")).click()
            }
            findElement(By.xpath("//input[@aria-label='Название']")).sendKeys("New blog $randomNum")
            findElement(By.xpath("//div[@role='button']/span[span='Далее']")).click()
            findElement(By.xpath("//input[@aria-label='Адрес']")).sendKeys("seTpo$randomNum")
            WebDriverWait(this, Duration.ofSeconds(3).toSeconds()).until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[.='Этот адрес блога не занят.']")
                )
            )
            WebDriverWait(
                this,
                Duration.ofSeconds(3).toSeconds()
            ).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button']/span[span='Сохранить']")))
                .click()
            Assertions.assertTrue(findElements(By.xpath("//div[@aria-label='Tpo$randomNum']")).isNotEmpty())
        }
    }

    @Test
    fun createBlog() {
        driver.get(baseUrl)
        if (isLoginNeeded()) {
            login()
        }
        addNewBlog()
    }
}