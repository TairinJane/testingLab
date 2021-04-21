package lab3

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

fun WebDriver.clickByXpath(xpath: String) {
    findElement(By.xpath(xpath)).click()
}

fun WebElement.clickByXpath(xpath: String) {
    findElement(By.xpath(xpath)).click()
}

fun WebDriver.typeByXpath(xpath: String, text: String) {
    findElement(By.xpath(xpath)).sendKeys(text)
}

fun WebDriver.waitForAndClick(xpath: String, seconds: Long = 3) {
    WebDriverWait(
        this,
        Duration.ofSeconds(seconds).toSeconds()
    ).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
        .click()
}

fun WebDriver.waitForAndClick(element: WebElement, seconds: Long = 3) {
    WebDriverWait(
        this,
        Duration.ofSeconds(seconds).toSeconds()
    ).until(ExpectedConditions.elementToBeClickable(element))
        .click()
}

fun WebDriver.waitForAndType(xpath: String, text: String, seconds: Long = 3) {
    WebDriverWait(
        this,
        Duration.ofSeconds(seconds).toSeconds()
    ).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
        .sendKeys(text)
}

fun WebDriver.waitForAndClear(xpath: String, seconds: Long = 3) {
    WebDriverWait(
        this,
        Duration.ofSeconds(seconds).toSeconds()
    ).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).clear()
}

fun WebDriver.navigate(url: String) {
    navigate().to(url)
}

fun WebDriver.waitUntilPresent(xpath: String, seconds: Long = 3): WebElement? {
    return WebDriverWait(
        this,
        Duration.ofSeconds(seconds).toSeconds()
    ).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)))
}

fun WebDriver.waitUntilVisible(xpath: String, seconds: Long = 3): WebElement? {
    return WebDriverWait(
        this,
        Duration.ofSeconds(seconds).toSeconds()
    ).until(ExpectedConditions.visibilityOf(findElement(By.xpath(xpath))))
}

fun WebDriver.waitUntilAllVisible(xpath: String, seconds: Long = 3): MutableList<WebElement>? {
    return WebDriverWait(
        this,
        Duration.ofSeconds(seconds).toSeconds()
    ).until(ExpectedConditions.visibilityOfAllElements(findElement(By.xpath(xpath))))
}

fun WebDriver.waitUntilInvisible(xpath: String, seconds: Long = 3) {
    WebDriverWait(
        this,
        Duration.ofSeconds(seconds).toSeconds()
    ).until(ExpectedConditions.invisibilityOf(findElement(By.xpath(xpath))))
}