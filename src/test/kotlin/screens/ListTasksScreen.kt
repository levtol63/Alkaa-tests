package screens

import io.appium.java_client.AppiumBy
import org.openqa.selenium.By
//Проект
object ListTasksScreen {
    val TITLE_TASK: By = AppiumBy.xpath("(//android.widget.TextView[@text=\"Tasks\"])[1]")
    val SORT_PERS: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.CheckBox")
    val SORT_WORK: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.CheckBox")
    val SORT_SHOP: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.CheckBox")
    val SORT_NAME_SHOP: By = AppiumBy.xpath("//android.widget.TextView[@text=\"Shopping List\"]")
    val SORT_NAME_PERS: By = AppiumBy.xpath("//android.widget.TextView[@text=\"Personal\"]")
    val SORT_NAME_WORK: By = AppiumBy.xpath("//android.widget.TextView[@text=\"Work\"]")
    val ADD_TASK_BT: By = AppiumBy.xpath("//android.widget.Button")
    val OK_LABEL: By = AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"That's all clear\"]")
    val TEXT_TASKS_COMPLETED: By = AppiumBy.xpath("//android.widget.TextView[@text=\"Wow! All tasks are completed!\"]")
    val TAB_LABEL: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View[4]")
    val TAB_TEXT: By = AppiumBy.xpath("(//android.widget.TextView[@text=\"Tasks\"])[2]")

    val INPUT_FIELD_TASK_NAME: By = AppiumBy.xpath("//android.widget.EditText")
    val PERSONAL_СHECK_BOX: By = AppiumBy.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.CheckBox")
    val WORK_СHECK_BOX: By = AppiumBy.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.CheckBox")
    val SHOP_СHECK_BOX: By = AppiumBy.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.widget.CheckBox")
    val SELECT_ALARM_BT: By = AppiumBy.xpath("//android.view.View[@content-desc=\"Alarm\"]")
    val CREATE_TASK_BT: By = AppiumBy.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[4]/android.widget.Button")
    val NEXT_BT_ALARM: By = AppiumBy.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")
    val DATE_TWENTY_SIX_OCT: By = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Current selection: Monday, October 6, 2025\"]")
    val TIME_TWO_HOUR: By = AppiumBy.xpath("//android.view.View[@content-desc=\"2 o'clock\"]")
    val TIME_THIRTY_MINUTES: By = AppiumBy.xpath("//android.view.View[@content-desc=\"30 minutes\"]")
    val CONFIRM_BT_ALARM: By = AppiumBy.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")
    val TASK_ITEM: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View")
    val ALARM_TASK_ITEM: By = AppiumBy.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")
    val NAME_TASK_ITEM: By = AppiumBy.xpath("//android.widget.TextView[@text=\"New Task\"]")
    val WORK_TYPE_TASK: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View")
    val COMPLETED_TASK_RADIO_BT: By = AppiumBy.xpath("//android.widget.RadioButton")
    val COMPLETED_TOAST: By = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View[3]/android.view.View")
    val NAME_COMPLETED_TOAST: By = AppiumBy.xpath("//android.widget.TextView[@text=\"Task completed\"]")


}