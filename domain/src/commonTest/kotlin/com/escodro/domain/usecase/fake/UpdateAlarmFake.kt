package com.escodro.domain.usecase.fake

import com.escodro.domain.model.Task
import com.escodro.domain.usecase.alarm.UpdateAlarm

internal class UpdateAlarmFake : UpdateAlarm {

    // --- поля для проверок в тестах ---
    var wasInvoked: Boolean = false
        private set

    var lastTask: Task? = null
        private set

    private val updatedById = LinkedHashMap<Long, Task>()

    override suspend fun invoke(task: Task) {
        wasInvoked = true
        lastTask = task
        updatedById[task.id] = task
    }

    fun wasInvokedForId(id: Long): Boolean = updatedById.containsKey(id)

    fun clear() {
        wasInvoked = false
        lastTask = null
        updatedById.clear()
    }
}
