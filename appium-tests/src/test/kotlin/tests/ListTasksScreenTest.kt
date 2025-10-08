package tests

import base.BaseTest
import io.qameta.allure.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import screens.ListTasksScreen
import utils.WaitUtils
import kotlin.test.assertEquals

//Проект.
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@Epic("Alkaa App Tests")
@Feature("Экран списка заданий")
class ListTasksScreenTest : BaseTest() {

    @Test
    @Order(1)
    @Story("Отображение элементов на экране списка заданий")
    @Description("Проверка, что после запуска приложения корректно отображаются все элементы на экране списка заданий")
    @Severity(SeverityLevel.CRITICAL)
    fun testListTasksScreenElements() {

        Allure.step("Проверка отображение категорий заданий: Personal, Work, Shopping", Allure.ThrowableRunnableVoid {
            val persSort = WaitUtils.waitForVisible(driver, ListTasksScreen.SORT_PERS)
            assertTrue(persSort.isDisplayed, "Кнопка 'Personal' не отображается")

            val persSortText = WaitUtils.waitForVisible(driver, ListTasksScreen.SORT_NAME_PERS)
            assertEquals("Personal", persSortText.text, "Название категории 'Personal' некорректно")

            val workSort = WaitUtils.waitForVisible(driver, ListTasksScreen.SORT_WORK)
            assertTrue(workSort.isDisplayed, "Кнопка 'Work' не отображается")

            val workSortText = WaitUtils.waitForVisible(driver, ListTasksScreen.SORT_NAME_WORK)
            assertEquals("Work", workSortText.text, "Название категории 'Work' некорректно")

            val shopSort = WaitUtils.waitForVisible(driver, ListTasksScreen.SORT_SHOP)
            assertTrue(shopSort.isDisplayed, "Кнопка 'Shopping List' не отображается")

            val shopSortText = WaitUtils.waitForVisible(driver, ListTasksScreen.SORT_NAME_SHOP)
            assertEquals("Shopping List", shopSortText.text, "Название категории 'Shopping List' некорректно")
        })

        Allure.step("Проверка отображение остальных основных элементов интерфейса", Allure.ThrowableRunnableVoid {
            val okLabel = WaitUtils.waitForVisible(driver, ListTasksScreen.OK_LABEL)
            assertTrue(okLabel.isDisplayed, "Метка OK не отображается")

            val tasksCompText = WaitUtils.waitForVisible(driver, ListTasksScreen.TEXT_TASKS_COMPLETED)
            assertEquals("Wow! All tasks are completed!", tasksCompText.text, "Текст 'All tasks completed' некорректен")

            val addTaskButton = WaitUtils.waitForVisible(driver, ListTasksScreen.ADD_TASK_BT)
            assertTrue(addTaskButton.isDisplayed, "Кнопка добавления задачи не отображается")

            val titleScreenText = WaitUtils.waitForVisible(driver, ListTasksScreen.TITLE_TASK)
            assertEquals("Tasks", titleScreenText.text, "Заголовок экрана некорректный")

            val labelTab = WaitUtils.waitForVisible(driver, ListTasksScreen.TAB_LABEL)
            assertTrue(labelTab.isDisplayed, "Вкладка задач не отображается")

            val tabText = WaitUtils.waitForVisible(driver, ListTasksScreen.TAB_TEXT)
            assertEquals("Tasks", tabText.text, "Название вкладки некорректно")
        })
    }

    @Test
    @Order(2)
    @Story("Создание нового задания")
    @Description("Проверка создания нового задания")
    @Severity(SeverityLevel.CRITICAL)
    fun testCreateTask() {

        Allure.step("Открыть диалог создания нового задания", Allure.ThrowableRunnableVoid {
            val addTaskButton = WaitUtils.waitForVisible(driver, ListTasksScreen.ADD_TASK_BT)
            addTaskButton.click()
        })

        Allure.step("Заполнить данные нового задания", Allure.ThrowableRunnableVoid {
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
        })

        Allure.step("Сохранить задачу и проверить, что она появилась в списке", Allure.ThrowableRunnableVoid {
            val createTaskButton = WaitUtils.waitForVisible(driver, ListTasksScreen.CREATE_TASK_BT)
            createTaskButton.click()

            val taskItem = WaitUtils.waitForVisible(driver, ListTasksScreen.TASK_ITEM)
            assertTrue(taskItem.isDisplayed, "Созданное задание не отображается")

            val nameTaskItem = WaitUtils.waitForVisible(driver, ListTasksScreen.NAME_TASK_ITEM)
            assertEquals("New Task", nameTaskItem.text, "Имя созданного задания некорректно")

            val workTypeWork = WaitUtils.waitForVisible(driver, ListTasksScreen.WORK_TYPE_TASK)
            assertTrue(workTypeWork.isDisplayed, "Категория 'Work' не отображается у созданного задания")
        })
    }

    @Test
    @Order(3)
    @Story("Выполнение задания")
    @Description("Проверка, что есть возможность выполнить задание")
    @Severity(SeverityLevel.CRITICAL)
    fun testTaskCompleted() {

        Allure.step("Отметить задание как выполненное", Allure.ThrowableRunnableVoid {
            val compRadioButton = WaitUtils.waitForVisible(driver, ListTasksScreen.COMPLETED_TASK_RADIO_BT)
            compRadioButton.click()
        })

        Allure.step("Проверка отображение тоста о выполнении задания", Allure.ThrowableRunnableVoid {
            val compToast = WaitUtils.waitForVisible(driver, ListTasksScreen.COMPLETED_TOAST)
            assertTrue(compToast.isDisplayed, "Тост о выполнении задачи не отображается")

            val compNameToast = WaitUtils.waitForVisible(driver, ListTasksScreen.NAME_COMPLETED_TOAST)
            assertEquals("Task completed", compNameToast.text, "Текст тоста некорректен")
        })

        Allure.step("Проверка, что выполненное задание исчезло из списка", Allure.ThrowableRunnableVoid {
            val taskItem = driver.findElements(ListTasksScreen.TASK_ITEM)
            assertTrue(taskItem.isEmpty(), "Выполненная задача не должна отображаться в списке")
        })
    }

    @Test
    @Order(4)
    @Story("Проверка ошибочного сценария")
    @Description("Проверка, что после выполнения задания список обновляется и отображает только актуальные задачи")
    @Severity(SeverityLevel.NORMAL)
    fun testError() {

        Allure.step("Проверка, что список заданий не пуст", Allure.ThrowableRunnableVoid {
            val taskItem = driver.findElements(ListTasksScreen.TASK_ITEM)
            assertTrue(taskItem.isNotEmpty(), "Список заданий пуст, хотя должен содержать задачи")
        })
    }
}

