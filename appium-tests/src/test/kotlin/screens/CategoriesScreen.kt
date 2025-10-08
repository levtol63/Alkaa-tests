package screens

import io.appium.java_client.AppiumBy
import org.openqa.selenium.By
//Проект.
object CategoriesScreen {
    val TITLE_SCREEN: By = AppiumBy.xpath("(//android.widget.TextView[@text=\"Categories\"])[1]")
    val ITEM_PERSONAL: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]")
    val ITEM_WORK: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]")
    val ITEM_SHOP: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]")
    val NAME_ITEM_PERSONAL: By = AppiumBy.xpath("//android.widget.TextView[@text=\"Personal\"]")
    val NAME_ITEM_WORK: By = AppiumBy.xpath("//android.widget.TextView[@text=\"Work\"]")
    val NAME_ITEM_SHOP: By = AppiumBy.xpath("//android.widget.TextView[@text=\"Shopping List\"]")
    val ADD_BT: By = AppiumBy.xpath("//android.widget.Button")
    val LABEL_TAB: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[4]")
    val NAME_TAB: By = AppiumBy.xpath("(//android.widget.TextView[@text=\"Categories\"])[2]")
    val TAB_CATEGORIES_BT: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[4]")
    val INPUT_FIELD_NAME_CATEGORY: By = AppiumBy.xpath("//android.widget.EditText")
    val COLOR_CHECKBOX: By = AppiumBy.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View")
    val SAVE_BT: By = AppiumBy.xpath("//android.widget.Button")
    val NEW_ITEM_GYM: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[4]")
    val NAME_NEW_ITEM_GYM: By = AppiumBy.xpath("//android.widget.TextView[@text=\"GYM\"]")

}