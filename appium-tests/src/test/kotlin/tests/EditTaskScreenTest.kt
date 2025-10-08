package tests

import base.BaseTest
import io.qameta.allure.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.remote.RemoteWebElement
import screens.EditTaskScreen
import screens.ListTasksScreen
import utils.WaitUtils
import kotlin.collections.mapOf
import kotlin.test.assertEquals
//Проект
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@Epic("Alkaa App Tests")
@Feature("Экран редактирования задания")
class EditTaskScreenTest : BaseTest() {

    @Test
    @Order(1)
    @Story("Отображение элементов экрана редактирования")
    @Description("Проверка, что после создания задания открывается экран редактирования и на нём присутствуют все элементы интерфейса")
    @Severity(SeverityLevel.CRITICAL)
    fun testEditScreenElements() {
        Allure.step(
            "Предусловие: Создать новое задание",
            Allure.ThrowableRunnableVoid {
                val addTaskButton = WaitUtils.waitForVisible(driver, ListTasksScreen.ADD_TASK_BT)
                addTaskButton.click()

                val inputFieldTaskName = WaitUtils.waitForVisible(driver, ListTasksScreen.INPUT_FIELD_TASK_NAME)
                inputFieldTaskName.sendKeys("New Task")

                val workCheckBox = WaitUtils.waitForVisible(driver, ListTasksScreen.WORK_СHECK_BOX)
                workCheckBox.click()

                val openSelectAlarmButton = WaitUtils.waitForVisible(driver, ListTasksScreen.SELECT_ALARM_BT)
                openSelectAlarmButton.click()

                val nextButton = WaitUtils.waitForVisible(driver, ListTasksScreen.NEXT_BT_ALARM)
                nextButton.click()

                val confirmButton = WaitUtils.waitForVisible(driver, ListTasksScreen.CONFIRM_BT_ALARM)
                confirmButton.click()

                val createTaskButton = WaitUtils.waitForVisible(driver, ListTasksScreen.CREATE_TASK_BT)
                createTaskButton.click()

                val taskItem = WaitUtils.waitForVisible(driver, ListTasksScreen.TASK_ITEM)
                taskItem.click()
            }
        )

        Allure.step(
            "Шаг 1: Проверить отображение элементов на экране редактирования задания",
            Allure.ThrowableRunnableVoid {
                val backButton = WaitUtils.waitForVisible(driver, EditTaskScreen.BACK_BT)
                assertTrue(backButton.isDisplayed, "Кнопка 'Назад' не отображается")

                val inputFieldNameTask = WaitUtils.waitForVisible(driver, EditTaskScreen.INPUT_FIELD_NAME_TASK)
                assertTrue(inputFieldNameTask.isDisplayed, "Поле ввода названия задачи не отображается")

                val compLabel = WaitUtils.waitForVisible(driver, EditTaskScreen.CATEGORIES_LABEL)
                assertTrue(compLabel.isDisplayed, "Метка категорий не отображается")

                val alarmLabel = WaitUtils.waitForVisible(driver, EditTaskScreen.ALARM_LABEL)
                assertTrue(alarmLabel.isDisplayed, "Метка напоминаний не отображается")

                val repeatLabel = WaitUtils.waitForVisible(driver, EditTaskScreen.REPEAT_LABEL)
                assertTrue(repeatLabel.isDisplayed, "Метка повторений не отображается")

                val personalCheckbox = WaitUtils.waitForVisible(driver, EditTaskScreen.PERSONAL_CHECKBOX)
                assertTrue(personalCheckbox.isDisplayed, "Чекбокс 'Personal' не отображается")

                val workCheckbox = WaitUtils.waitForVisible(driver, EditTaskScreen.WORK_CHECKBOX)
                assertTrue(workCheckbox.isDisplayed, "Чекбокс 'Work' не отображается")

                val shopCheckbox = WaitUtils.waitForVisible(driver, EditTaskScreen.SHOP_CHECKBOX)
                assertTrue(shopCheckbox.isDisplayed, "Чекбокс 'Shopping' не отображается")

                val noticeLabel = WaitUtils.waitForVisible(driver, EditTaskScreen.NOTICE_LABEL)
                assertTrue(noticeLabel.isDisplayed, "Метка 'Notice' не отображается")

                val personalNameCB = WaitUtils.waitForVisible(driver, EditTaskScreen.NAME_PERSONAL_CB)
                assertTrue(personalNameCB.isDisplayed, "Название чекбокса 'Personal' не отображается")

                val workNameCB = WaitUtils.waitForVisible(driver, EditTaskScreen.NAME_WORK_CB)
                assertTrue(workNameCB.isDisplayed, "Название чекбокса 'Work' не отображается")

                val shopNameCB = WaitUtils.waitForVisible(driver, EditTaskScreen.NAME_SHOP_CB)
                assertTrue(shopNameCB.isDisplayed, "Название чекбокса 'Shopping' не отображается")

                val inputFieldNotice = WaitUtils.waitForVisible(driver, EditTaskScreen.INPUT_FIELD_NOTICE)
                assertTrue(inputFieldNotice.isDisplayed, "Поле 'Notice' не отображается")

                val deleteAlarmButton = WaitUtils.waitForVisible(driver, EditTaskScreen.DELETE_ALARM_BT)
                assertTrue(deleteAlarmButton.isDisplayed, "Кнопка удаления напоминания не отображается")

                val neverText = WaitUtils.waitForVisible(driver, EditTaskScreen.NEVER_TEXT)
                assertTrue(neverText.isDisplayed, "Текст 'Never' не отображается")
            }
        )
    }

    @Test
    @Order(2)
    @Story("Редактирование задания")
    @Description("Проверка, что можно изменить данные существующего задания и изменения сохраняются корректно")
    @Severity(SeverityLevel.CRITICAL)
    fun testEditTask() {
        Allure.step(
            "Шаг 1: Изменить параметры существующего задания",
            Allure.ThrowableRunnableVoid {
                val inputFieldNameTask = WaitUtils.waitForVisible(driver, EditTaskScreen.INPUT_FIELD_NAME_TASK)

                (driver as JavascriptExecutor).executeScript(
                    "mobile: replaceElementValue",
                    mapOf(
                        "elementId" to (inputFieldNameTask as RemoteWebElement).id,
                        "text" to "" // <-- правильный параметр
                    )
                )
                inputFieldNameTask.sendKeys("Update name task")

                val personalCheckbox = WaitUtils.waitForVisible(driver, EditTaskScreen.PERSONAL_CHECKBOX)
                personalCheckbox.click()

                val inputFieldNotice = WaitUtils.waitForVisible(driver, EditTaskScreen.INPUT_FIELD_NOTICE)
                inputFieldNotice.sendKeys("New notice")

                val deleteAlarmButton = WaitUtils.waitForVisible(driver, EditTaskScreen.DELETE_ALARM_BT)
                deleteAlarmButton.click()

                val backButton = WaitUtils.waitForVisible(driver, EditTaskScreen.BACK_BT)
                backButton.click()
            }
        )

        Allure.step(
            "Шаг 2: Проверить, что изменения сохранились",
            Allure.ThrowableRunnableVoid {
                val taskItem = WaitUtils.waitForVisible(driver, ListTasksScreen.TASK_ITEM)
                taskItem.click()

                val nameTask = WaitUtils.waitForVisible(driver, EditTaskScreen.NAME_TASK)
                assertEquals("Update name task", nameTask.text, "Название задания не обновилось")

                val inputFieldNotice = WaitUtils.waitForVisible(driver, EditTaskScreen.NOTICE_TEXT)
                assertEquals("New notice", inputFieldNotice.text, "Текст заметки не обновился")

                val neverText = driver.findElements(EditTaskScreen.NEVER_TEXT)
                assertTrue(neverText.isEmpty(), "Элемент 'Never' не должен отображаться после удаления напоминания")
            }
        )
    }

    @Test
    @Order(3)
    @Story("Проверка ошибочного сценария")
    @Description("Проверка, что отображается правильный текст после редактирования задания")
    @Severity(SeverityLevel.NORMAL)
    fun testError() {
        Allure.step(
            "Проверить актуальное имя задания после редактирования",
            Allure.ThrowableRunnableVoid {
                val nameTask = WaitUtils.waitForVisible(driver, EditTaskScreen.NAME_TASK)
                assertEquals("New task", nameTask.text, "Текст задания не соответствует ожидаемому")
            }
        )
    }
}