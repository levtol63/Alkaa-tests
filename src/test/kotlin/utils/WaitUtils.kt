package utils

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
//Проект
object WaitUtils {

    private const val DEFAULT_TIMEOUT = 10L

    fun waitForVisible(driver: AndroidDriver, locator: By, timeout: Long = DEFAULT_TIMEOUT): WebElement {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeout))
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
    }

    fun waitForClickable(driver: AndroidDriver, locator: By, timeout: Long = DEFAULT_TIMEOUT): WebElement {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeout))
        return wait.until(ExpectedConditions.elementToBeClickable(locator))
    }

    fun waitForPresence(driver: AndroidDriver, locator: By, timeout: Long = DEFAULT_TIMEOUT): WebElement {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeout))
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator))
    }

    fun waitUntilGone(driver: AndroidDriver, locator: By, timeout: Long = DEFAULT_TIMEOUT): Boolean {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeout))
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator))
    }
}
