package com.escodro.domain.unittests.fakes
import com.escodro.domain.interactor.GlanceInteractor
//Проект
internal class GlanceInteractorFake : GlanceInteractor {

    var wasNotified: Boolean = false

    override suspend fun onTaskListUpdated() {
        wasNotified = true
    }

    fun clean() {
        wasNotified = false
    }
}
