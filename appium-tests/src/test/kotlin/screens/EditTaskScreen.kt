package screens

import io.appium.java_client.AppiumBy
import org.openqa.selenium.By
//Проект.
object EditTaskScreen {
    val BACK_BT: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.widget.Button")
    val INPUT_FIELD_NAME_TASK: By = AppiumBy.xpath("//android.widget.EditText[@text=\"New Task\"]")
    val CATEGORIES_LABEL: By = AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Categories\"]")
    val NOTICE_LABEL: By = AppiumBy.xpath("//android.view.View[@content-desc=\"Description\"]")
    val ALARM_LABEL: By = AppiumBy.xpath("//android.view.View[@content-desc=\"Alarm\"]")
    val REPEAT_LABEL: By = AppiumBy.xpath("//android.view.View[@content-desc=\"Repeating alarm\"]")
    val PERSONAL_CHECKBOX: By = AppiumBy.xpath("//android.widget.ScrollView/android.view.View[1]/android.view.View[1]/android.widget.CheckBox")
    val WORK_CHECKBOX: By = AppiumBy.xpath("//android.widget.ScrollView/android.view.View[1]/android.view.View[2]/android.widget.CheckBox")
    val SHOP_CHECKBOX: By = AppiumBy.xpath("//android.widget.ScrollView/android.view.View[1]/android.view.View[3]/android.widget.CheckBox")
    val INPUT_FIELD_NOTICE: By = AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[2]")
    val DELETE_ALARM_BT: By = AppiumBy.xpath("//android.widget.ScrollView/android.view.View[2]/android.view.View[2]/android.widget.Button")
    val NEVER_TEXT: By = AppiumBy.xpath("//android.widget.TextView[@text=\"Never\"]")
    val NAME_PERSONAL_CB: By = AppiumBy.xpath("//android.view.View[@content-desc=\"Description\"]")
    val NAME_WORK_CB: By = AppiumBy.xpath("//android.view.View[@content-desc=\"Alarm\"]")
    val NAME_SHOP_CB: By = AppiumBy.xpath("//android.view.View[@content-desc=\"Repeating alarm\"]")

    val NAME_TASK: By = AppiumBy.xpath("//android.widget.EditText[@text=\"Update name task\"]")
    val NOTICE_TEXT: By = AppiumBy.xpath("//android.widget.EditText[@text=\"New notice\"]")
}