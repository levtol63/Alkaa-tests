package base

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.junit.jupiter.api.*
import java.net.URL
import java.time.Duration
//Проект.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTest {

    protected lateinit var driver: AndroidDriver

    protected val appPackage = "com.escodro.alkaa"
    protected val appActivity = "com.escodro.alkaa.MainActivity"

    @BeforeAll
    fun setUp() {
        val options = UiAutomator2Options()
            .setPlatformName("Android")
            .setAutomationName("UiAutomator2")
            .setUdid("emulator-5554")
            .setDeviceName("Android Emulator")
            .setApp("/Users/nikitasanin/AndroidStudioProjects/alkaa/app/build/outputs/apk/debug/alkaa-3.3.1-debug.apk")
            .setAppPackage(appPackage)
            .setAppActivity(appActivity)
            .setAutoGrantPermissions(true)

        driver = AndroidDriver(URL("http://127.0.0.1:4723"), options)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3))
    }

    @AfterAll
    fun tearDown() {
        try {
            driver.terminateApp(appPackage)
        } catch (_: Exception) {
        }
        if (::driver.isInitialized) driver.quit()
    }
}
