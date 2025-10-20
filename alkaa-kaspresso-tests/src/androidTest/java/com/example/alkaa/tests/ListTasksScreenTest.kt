package com.example.alkaa.tests

import android.content.ComponentName
import com.example.alkaa.screens.ListTasksScreen
import com.kaspersky.components.alluresupport.withForcedAllureSupport

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Before
import org.junit.Test

class ListTasksScreenTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withForcedAllureSupport()
) {

    @Before
    fun launchApp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val appId = "com.escodro.alkaa"

        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
            component = ComponentName(appId, "$appId.MainActivity")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        instrumentation.startActivitySync(intent)
        instrumentation.waitForIdleSync()
    }


    @Test
    fun testListTasksScreenElements() = run {
        step("Проверка отображения категорий задач") {
            ListTasksScreen {
                sortPersCheckBox.isVisible()
                sortNamePers.hasText("Personal")

                sortWorkCheckBox.isVisible()
                sortNameWork.hasText("Work")

                sortShopCheckBox.isVisible()
                sortNameShop.hasText("Shopping List")
            }
        }

        step("Проверка основных элементов интерфейса") {
            ListTasksScreen {
                titleTask.hasText("Tasks")
                addTaskButton.isVisible()
                okLabel.isVisible()
                textTasksCompleted.hasText("Wow! All tasks are completed!")
                tabLabel.isVisible()
                tabText.hasText("Tasks")
            }
        }
    }
}