package lab3

import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import kotlin.random.Random.Default.nextInt

import org.openqa.selenium.interactions.Actions


class CreationTest : BaseTest() {

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

    @Test
    fun addDraftPost() {
        driver.run {
            clickByXpath("//div[@role='button'][@aria-label='Создать новое сообщение']")
            findElement(By.xpath("//input[@aria-label='Название']")).apply {
                click()
                sendKeys("Draft post")
            }
            switchTo().frame(findElement(By.xpath("//iframe[contains(@class, 'editable')]")))
            typeByXpath("//body", "Post body")
            switchTo().defaultContent()
            clickByXpath("//span[@title='Изменения сохранены.']")
            waitForAndClick(
                "//div[@role='button'][@aria-label='Назад']",
                5
            )
            findElements(By.xpath("//div[@role='listitem']")).apply {
                Assertions.assertTrue(findElements(By.xpath(".//span[.='Draft post']")).isNotEmpty())
                Assertions.assertTrue(findElements(By.xpath(".//div[.='Черновик']")).isNotEmpty())
            }
        }
    }

    @Test
    fun deletePost() {
        driver.run {
            val postTitle = "Post to delete"
            clickByXpath("//div[@role='button'][@aria-label='Создать новое сообщение']")
            findElement(By.xpath("//input[@aria-label='Название']")).apply {
                click()
                sendKeys(postTitle)
            }
            clickByXpath("//span[@title='Изменения сохранены.']")
            waitForAndClick(
                "//div[@role='button'][@aria-label='Опубликовать'][@data-tooltip='Опубликовать']/*//div[.='Опубликовать']",
                5
            )
            waitForAndClick("//span[.='При этом сообщение будет опубликовано в вашем блоге.']/following-sibling::div/div[@role='button']/span/span[.='ОК']")
            Assertions.assertTrue(findElements(By.xpath("//div[@role='listitem']//span[.='$postTitle']")).isNotEmpty())

            val builder = Actions(this)
            val postElement =
                findElement(By.xpath("//c-wiz[@aria-hidden='false']//div[@role='listitem'][.//span='$postTitle']"))
            builder.moveToElement(postElement).build().perform()

            val deleteButton = findElement(
                By.xpath("//c-wiz[@aria-hidden='false']//div[@role='listitem']//div[@role='button'][@aria-label='Удалить это сообщение'][@data-tooltip='Удалить это сообщение']")
            )
            waitForAndClick(deleteButton)

            val okButton = findElements(By.xpath("//div[@role='button']/span[span='Удалить сообщение']")).last()
            waitForAndClick(okButton)

            val deletedPost = findElement(By.xpath("//div[@role='listitem']//span[.='$postTitle']"))
            WebDriverWait(
                this,
                Duration.ofSeconds(3).toSeconds()
            ).until(ExpectedConditions.invisibilityOf(deletedPost))
            Assertions.assertTrue(!deletedPost.isDisplayed)
        }
    }
}