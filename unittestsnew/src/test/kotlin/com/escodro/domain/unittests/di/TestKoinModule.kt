package com.escodro.domain.unittests.di

import com.escodro.domain.interactor.GlanceInteractor
import com.escodro.domain.repository.TaskRepository
import com.escodro.domain.unittests.fakes.GlanceInteractorFake
import com.escodro.domain.unittests.fakes.TaskRepositoryFake
import com.escodro.domain.unittests.fakes.UpdateAlarmFake
import com.escodro.domain.usecase.alarm.UpdateAlarm
import com.escodro.domain.usecase.task.implementation.AddTaskImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val testModule = module {

    single { TaskRepositoryFake() } bind TaskRepository::class
    single { UpdateAlarmFake() } bind UpdateAlarm::class
    single { GlanceInteractorFake() } bind GlanceInteractor::class

    factoryOf(::AddTaskImpl)
}
