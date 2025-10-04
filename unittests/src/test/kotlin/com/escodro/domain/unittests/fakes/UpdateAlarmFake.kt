package com.escodro.domain.unittests.fakes

import com.escodro.domain.model.Task
import com.escodro.domain.usecase.alarm.UpdateAlarm
//Проект
class UpdateAlarmFake : UpdateAlarm {

    var wasInvoked: Boolean = false
        private set

    var lastTask: Task? = null
        private set

    private val invokedIds = mutableSetOf<Long>()

    override suspend fun invoke(task: Task) {
        wasInvoked = true
        lastTask = task
        invokedIds.add(task.id)
    }

    fun wasInvokedForId(id: Long): Boolean = invokedIds.contains(id)

    fun hasLastTask(): Boolean = lastTask != null

    fun requireLastTask(): Task =
        requireNotNull(lastTask) { "Будильник ещё не был вызван" }

    fun reset() {
        wasInvoked = false
        lastTask = null
        invokedIds.clear()
    }
}
