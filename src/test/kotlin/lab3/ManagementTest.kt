package lab3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class ManagementTest: BaseTest() {
    private val postTitle = "Post 1"
    private val tag = "tag"

    @Test
    @Disabled
    fun viewPost() {
        driver.run {
            hoverPost(postTitle)
            waitForAndClick("//div[@role='listitem'][.//span='$postTitle']//div[@role='presentation'][@aria-label='Посмотреть']")

            try {
                val titleElement = waitUntilPresent("//h3[@class='post-title entry-title']", 10)
                Assertions.assertEquals(postTitle, titleElement?.text)
            } catch (e: Exception) {
                println(driver.currentUrl)
            }
        }
    }

    @Test
    fun addAndDeleteTag() {
        val notificationXpath = "//div[.='Ярлык обновлен'][@id]"
        driver.run {
            hoverPost(postTitle)
            waitForAndClick("//div[@role='listitem'][.//span='$postTitle']//div[@role='button'][@aria-label='Применить ярлыки для этого сообщения']")
            waitForAndType("//textarea[@aria-label='Разделите ярлыки запятыми']", tag)
            clickByXpath("//div[@role='button']/span[span='Применить']")
            waitUntilVisible(notificationXpath, 5)
            Assertions.assertTrue(findElements(By.xpath("//div[@role='listitem'][.//span='$postTitle']//span[@data-label='$tag']")).isNotEmpty())

            waitUntilInvisible(notificationXpath)
            hoverPost(postTitle)
            waitForAndClick("//div[@role='listitem'][.//span='$postTitle']//div[@role='button'][@aria-label='Применить ярлыки для этого сообщения']", 5)
            waitForAndClear("//textarea[@aria-label='Разделите ярлыки запятыми']")
            clickByXpath("//div[@role='button']/span[span='Применить']")
            waitUntilVisible(notificationXpath, 5)
            Assertions.assertTrue(findElements(By.xpath("//div[@role='listitem'][.//span='$postTitle']//span[@data-label='$tag']")).isEmpty())
        }
    }

    @Test
    fun filterByTag() {
        driver.run {
            clickByXpath("//div[@role='button'][@aria-label='Filter by label']")
            waitForAndType("//textarea[@aria-label='Разделите ярлыки запятыми']", tag)
            clickByXpath("//div[@role='button']/span[span='Применить']")

            waitUntilAllVisible("//div[@role='listitem']")?.forEach { post ->
                Assertions.assertTrue(post.findElement(By.xpath("//span[@data-label='$tag']")).isDisplayed)
            }
        }
    }

    @Test
    fun moveToDraft() {
        driver.run {
            hoverPost(postTitle)
            waitForAndClick("//div[@role='listitem'][.//span='$postTitle']//div[@role='button'][@aria-label='Переместить в черновики']")
            waitUntilVisible("//div[.='Публикация сообщения отменена.'][@id]", 5)
            findElements(By.xpath("//div[@role='listitem']")).apply {
                Assertions.assertTrue(findElements(By.xpath(".//span[.='$postTitle']")).isNotEmpty())
                Assertions.assertTrue(findElements(By.xpath(".//div[.='Черновик']")).isNotEmpty())
            }

            waitUntilInvisible("//div[.='Публикация сообщения отменена.'][@id]", 5)
            hoverPost(postTitle)
            waitForAndClick("//div[@role='listitem'][.//span='$postTitle']//div[@role='button'][@aria-label='Опубликовать']")
            waitUntilVisible("//div[.='Сообщение опубликовано.'][@id]", 5)
            findElements(By.xpath("//div[@role='listitem']")).apply {
                Assertions.assertTrue(findElements(By.xpath(".//span[.='$postTitle']")).isNotEmpty())
                Assertions.assertTrue(findElements(By.xpath(".//div[.='Черновик']")).isEmpty())
            }
        }
    }
}