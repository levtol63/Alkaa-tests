package tests
import base.BaseTest
import io.qameta.allure.Allure
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.Story
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import screens.CategoriesScreen
import utils.WaitUtils
import kotlin.test.assertEquals

//Проект.
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@Epic("Alkaa App Tests")
@Feature("Экран категорий заданий")
class CategoriesScreenTest : BaseTest() {

    @Test
    @Order(1)
    @Story("Отображение элементов на экране категорий")
    @Description("Проверка, что после перехода на экран категорий корректно отображаются все основные элементы интерфейса")
    @Severity(SeverityLevel.CRITICAL)
    fun testСategoriesTasksScreenElements() {

        Allure.step("Открыть экран категорий через нижнюю навигацию", Allure.ThrowableRunnableVoid {
            val tabButton = WaitUtils.waitForVisible(driver, CategoriesScreen.TAB_CATEGORIES_BT)
            tabButton.click()
        })

        Allure.step("Проверить наличие и корректность отображения основных элементов на экране категорий", Allure.ThrowableRunnableVoid {
            val nameTitle = WaitUtils.waitForVisible(driver, CategoriesScreen.TITLE_SCREEN)
            assertTrue(nameTitle.isDisplayed, "Заголовок экрана категорий не отображается")

            val itemPersonal = WaitUtils.waitForVisible(driver, CategoriesScreen.ITEM_PERSONAL)
            assertTrue(itemPersonal.isDisplayed, "Элемент категории 'Personal' не отображается")

            val itemWork = WaitUtils.waitForVisible(driver, CategoriesScreen.ITEM_WORK)
            assertTrue(itemWork.isDisplayed, "Элемент категории 'Work' не отображается")

            val itemShop = WaitUtils.waitForVisible(driver, CategoriesScreen.ITEM_SHOP)
            assertTrue(itemShop.isDisplayed, "Элемент категории 'Shopping' не отображается")

            val nameItemPersonal = WaitUtils.waitForVisible(driver, CategoriesScreen.NAME_ITEM_PERSONAL)
            assertEquals("Personal", nameItemPersonal.text, "Название категории 'Personal' некорректно")

            val nameItemWork = WaitUtils.waitForVisible(driver, CategoriesScreen.NAME_ITEM_WORK)
            assertEquals("Work", nameItemWork.text, "Название категории 'Work' некорректно")

            val nameItemShop = WaitUtils.waitForVisible(driver, CategoriesScreen.NAME_ITEM_SHOP)
            assertEquals("Shopping List", nameItemShop.text, "Название категории 'Shopping' некорректно")

            val addButton = WaitUtils.waitForVisible(driver, CategoriesScreen.ADD_BT)
            assertTrue(addButton.isDisplayed, "Кнопка добавления новой категории не отображается")

            val nameTab = WaitUtils.waitForVisible(driver, CategoriesScreen.NAME_TAB)
            assertEquals("Categories", nameTab.text, "Название вкладки некорректно")

            val labelTab = WaitUtils.waitForVisible(driver, CategoriesScreen.LABEL_TAB)
            assertTrue(labelTab.isDisplayed, "Иконка вкладки 'Categories' не отображается")
        })
    }

    @Test
    @Order(2)
    @Story("Создание новой категории")
    @Description("Проверка, что пользователь может создать новую категорию и она корректно отображается в списке")
    @Severity(SeverityLevel.CRITICAL)
    fun testCreateCategory() {

        Allure.step("Открыть диалог создания новой категории", Allure.ThrowableRunnableVoid {
            val addCategoryButton = WaitUtils.waitForVisible(driver, CategoriesScreen.ADD_BT)
            addCategoryButton.click()
        })

        Allure.step("Ввести данные новой категории", Allure.ThrowableRunnableVoid {
            val inputFieldCatName =
                WaitUtils.waitForVisible(driver, CategoriesScreen.INPUT_FIELD_NAME_CATEGORY)
            inputFieldCatName.sendKeys("GYM")

            val colorCheckBox = WaitUtils.waitForVisible(driver, CategoriesScreen.COLOR_CHECKBOX)
            colorCheckBox.click()
        })

        Allure.step("Сохранить новую категорию и проверить её отображение в списке", Allure.ThrowableRunnableVoid {
            val saveButton = WaitUtils.waitForVisible(driver, CategoriesScreen.SAVE_BT)
            saveButton.click()

            val newItemCat = WaitUtils.waitForVisible(driver, CategoriesScreen.NEW_ITEM_GYM)
            assertTrue(newItemCat.isDisplayed, "Созданная категория 'GYM' не отображается в списке")

            val nameNewItemCat = WaitUtils.waitForVisible(driver, CategoriesScreen.NAME_NEW_ITEM_GYM)
            assertEquals("GYM", nameNewItemCat.text, "Название новой категории отображается некорректно")
        })
    }

    @Test
    @Order(3)
    @Story("Проверка ошибочного сценария")
    @Description("Проверка отображение корреткного названия на карточке новой категории на экране категорий")
    @Severity(SeverityLevel.NORMAL)
    fun testError() {

        Allure.step("Проверить, что отображается корреткное название на карточке новой категории", Allure.ThrowableRunnableVoid {
            val nameNewItemCat = WaitUtils.waitForVisible(driver, CategoriesScreen.NAME_NEW_ITEM_GYM)
            assertEquals("HOME WORK", nameNewItemCat.text, "Имя категории должно быть 'GYM'")
        })
    }
}

