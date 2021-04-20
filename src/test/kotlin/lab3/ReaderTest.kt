package lab3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class ReaderTest : BaseTest("https://setpo4.blogspot.com/") {

    @Test
    fun readBlog() {
        driver.run {
            Assertions.assertEquals("Tpo3", findElement(By.xpath("//div[@name='Заголовок']//h1")).text)
            findElements(By.xpath("//h3[contains(@class, 'post-title')]/a")).forEachIndexed { index, element ->
                Assertions.assertEquals("Post ${index + 1}", element.text)
            }
            findElements(By.xpath("//div[contains(@class,'snippet-item')]")).forEachIndexed { index, element ->
                Assertions.assertEquals("Post ${index + 1} body", element.text.trim())
            }
        }
    }

    @Test
    fun openPost() {
        val postTitle = "Post 1"
        driver.run {
            clickByXpath("//h3[contains(@class, 'post-title')]/a[.='$postTitle']")
            Assertions.assertEquals(postTitle, findElement(By.xpath("//h3[contains(@class, 'post-title')]")).text)
            Assertions.assertEquals(
                "$postTitle body",
                findElement(By.xpath("//div[contains(@class, 'post-body')]/p")).text.trim()
            )
        }
    }

    private fun addCommentToPost(postTitle: String, commentText: String) {
        driver.run {
            clickByXpath("//h3[contains(@class, 'post-title')]/a[.='$postTitle']")
            waitForAndClick("//div[@class='comment-form']", 10)
            switchTo().frame("comment-editor")
            typeByXpath("//textarea[@name='commentBody']", commentText)
            clickByXpath("//input[@name='postCommentSubmit']")
            switchTo().defaultContent()
            fillLoginFormIfNeeded()
        }
    }

    @Test
    fun addComment() {
        val postTitle = "Post 1"
        val commentText = "Comment"
        driver.run {
            addCommentToPost(postTitle, commentText)
            Assertions.assertTrue(findElement(By.xpath("//p[@class='comment-content'][.='$commentText']")).isDisplayed)
        }
    }

    @Test
    fun deleteComment() {
        val postTitle = "Post 2"
        val commentText = "Comment to delete"
        driver.run {
            addCommentToPost(postTitle, commentText)
            clickByXpath("//li[@class='comment'][.//p[.='$commentText']]//a[.='Удалить']")
            clickByXpath("//input[@name='removeForever']")
            clickByXpath("//a[@id='removeBtn']")
            waitForAndClick("//div[@class='contents']//a")
            Assertions.assertTrue(findElements(By.xpath("//p[@class='comment-content'][.='$commentText']")).isEmpty())
        }
    }

    @Test
    fun openAuthorProfile() {
        val blogTitle = "Tpo3"
        driver.run {
            clickByXpath("//a[contains(@class, 'profile-link')]")
            Assertions.assertEquals(blogTitle, findElement(By.xpath("//div[@class='vcard']/h1")).text)
            Assertions.assertTrue(findElements(By.xpath("//li[@class='sidebar-item']//a")).any {
                it.text == blogTitle
            })
        }
    }

    @Test
    fun replyToComment() {
        val commentToReply = "Comment to reply"
        val replyText = "Reply to comment"
        driver.run {
            addCommentToPost("Post 2", commentToReply)
            clickByXpath("//li[@class='comment'][.//p[.='$commentToReply']]//a[.='Ответить']")
            waitForAndClick("//div[@class='comment-replybox-single']")
            switchTo().frame("comment-editor")
            waitForAndType("//textarea[@name='commentBody']", replyText)
            clickByXpath("//input[@name='postCommentSubmit']")
            switchTo().defaultContent()
            fillLoginFormIfNeeded()
            Assertions.assertTrue(
                findElement(
                    By.xpath(
                        "//li[@class='comment'][.//p[.='$commentToReply']]" +
                                "//div[@class='comment-replies']//p[@class='comment-content'][.='$replyText']"
                    )
                ).isDisplayed
            )
        }
    }

    @Test
    fun searchForPost() {
        val searchText = "Post 2"
        driver.run {
            clickByXpath("//button[@aria-label='Поиск']")
            waitForAndType("//input[@aria-label='Поиск по этому блогу']", searchText)
            clickByXpath("//input[@value='Поиск']")
            Assertions.assertEquals(searchText, findElement(By.xpath("//span[@class='search-query']")).text)
            Assertions.assertTrue(findElements(By.xpath("//h3[contains(@class, 'post-title')]/a[.='$searchText']")).isNotEmpty())
        }
    }

    @Test
    fun copyPostLink() {
        driver.run {
            clickByXpath("//h3[contains(@class, 'post-title')]/a[.='Post 2']")
            clickByXpath("//button[@aria-label='Поделиться']")
            val shareLink = findElement(By.xpath("//ul[@aria-hidden='false']//span[@aria-label='Получить ссылку']"))
            Assertions.assertEquals(driver.currentUrl, shareLink.getAttribute("data-url"))
            shareLink.click()
            clickByXpath("//button[@name='ОК']")
        }
    }
}