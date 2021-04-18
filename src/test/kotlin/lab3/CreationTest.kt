package lab3

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.specs.StringSpec
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class CreationTest : StringSpec() {
    private val driver: WebDriver = ChromeDriver()
    private val signupUrl = "https://account.mail.ru/signup"
    private val baseUrl = "https://www.blogger.com"

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()

        "Open mail" {
            driver.run {
                get(signupUrl)
                pageSource.shouldContain("Регистрация")
                quit()
            }
        }

        "New blog" {
            driver.run {
                findElement(By.xpath("//div[@role='listbox'][@aria-label='Blog Selection']/div[@role='presentation']")).click()
                findElement(By.xpath("//span[.='Новый блог…']")).click()
                findElement(By.xpath("//input[@aria-label='Название']")).sendKeys("Tpo3")
            }
        }

    }
}