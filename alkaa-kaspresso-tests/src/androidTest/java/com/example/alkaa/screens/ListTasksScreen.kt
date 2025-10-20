package com.example.alkaa.screens


import com.kaspersky.kaspresso.screens.KScreen

import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.view.View
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object ListTasksScreen : KScreen<ListTasksScreen>() {
    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null
    val titleTask = KTextView { withText("Tasks") }

    // Сортировка
    val sortPersCheckBox = KCheckBox { isAssignableFrom(CheckBox::class.java) }
    val sortWorkCheckBox = KCheckBox { isAssignableFrom(CheckBox::class.java) }
    val sortShopCheckBox = KCheckBox { isAssignableFrom(CheckBox::class.java) }

    val sortNameShop = KTextView { withText("Shopping List") }
    val sortNamePers = KTextView { withText("Personal") }
    val sortNameWork = KTextView { withText("Work") }

    // Кнопки
    val addTaskButton = KButton { isAssignableFrom(Button::class.java) }
    val okLabel = KButton { withContentDescription("That's all clear") }

    // Сообщения
    val textTasksCompleted = KTextView { withText("Wow! All tasks are completed!") }
    val completedToast = KTextView { withText("Task completed") }

    // Вкладки
    val tabLabel = KView { isAssignableFrom(View::class.java) }
    val tabText = KTextView { withText("Tasks") }

    // Поля ввода и чекбоксы при создании задачи
    val inputFieldTaskName = KEditText { isAssignableFrom(EditText::class.java) }
    val personalCheckBox = KCheckBox { isAssignableFrom(CheckBox::class.java) }
    val workCheckBox = KCheckBox { isAssignableFrom(CheckBox::class.java) }
    val shopCheckBox = KCheckBox { isAssignableFrom(CheckBox::class.java) }

    val selectAlarmButton = KButton { withContentDescription("Alarm") }
    val createTaskButton = KButton { isAssignableFrom(Button::class.java) }

    // Настройка времени и даты
    val nextButtonAlarm = KButton { isAssignableFrom(Button::class.java) }
    val dateTwentySixOct = KTextView { withContentDescription("Current selection: Monday, October 6, 2025") }
    val timeTwoHour = KTextView { withContentDescription("2 o'clock") }
    val timeThirtyMinutes = KTextView { withContentDescription("30 minutes") }
    val confirmButtonAlarm = KButton { isAssignableFrom(Button::class.java) }

    // Элементы списка задач
    val taskItem = KView { isAssignableFrom(View::class.java) }
    val alarmTaskItem = KButton { isAssignableFrom(Button::class.java) }
    val nameTaskItem = KTextView { withText("New Task") }
    val workTypeTask = KView { isAssignableFrom(View::class.java) }

    // Завершение задачи
    val completedTaskRadioButton = KCheckBox { isAssignableFrom(RadioButton::class.java) }
    val nameCompletedToast = KTextView { withText("Task completed") }
}
