package com.escodro.domain.unittests.tests

import com.escodro.domain.model.Task
import com.escodro.domain.unittests.fakes.GlanceInteractorFake
import com.escodro.domain.unittests.fakes.TaskRepositoryFake
import com.escodro.domain.unittests.fakes.UpdateAlarmFake
import com.escodro.domain.usecase.task.implementation.AddTaskImpl
import io.qameta.allure.Allure
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.api.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
//Проект
inline fun stepSuspend(name: String, crossinline block: suspend () -> Unit) {
    Allure.step(name, Allure.ThrowableRunnable<Unit> {
        runBlocking { block() }
    })
}

@Epic("Задачи")
@Feature("Добавление задачи")
class AddTaskAllureKoinTest : KoinTest {

    private val testModule = module {
        single { TaskRepositoryFake() }
        single { UpdateAlarmFake() }
        single { GlanceInteractorFake() }
    }

    private lateinit var repo: TaskRepositoryFake
    private lateinit var alarm: UpdateAlarmFake
    private lateinit var glance: GlanceInteractorFake

    @BeforeEach
    fun setUp() {
        startKoin { modules(testModule) }
        repo = get()
        alarm = get()
        glance = get()

        runBlocking { repo.cleanTable() }
        alarm.reset()
        glance.clean()
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
    }

    private fun newUse(glanceOverride: GlanceInteractorFake? = glance): AddTaskImpl =
        AddTaskImpl(repo, alarm, glanceOverride)

    private fun task(
        id: Long = 0L,
        title: String = "Купить хлеб",
        due: LocalDateTime? = null,
        completed: Boolean = false
    ) = Task(
        id = id,
        completed = completed,
        title = title,
        description = null,
        categoryId = null,
        dueDate = due,
        creationDate = null,
        completedDate = null,
        isRepeating = false,
        alarmInterval = null
    )

    @Test
    @Story("Добавление задачи с пустым заголовком")
    fun emptyTitle_doesNothing() = runTest {
        val use = newUse()

        stepSuspend("Добавление задачи с пустым заголовком") { use(task(title = "")) }
        stepSuspend("Проверка, что задача не сохранилась") {
            assertTrue(repo.findAllTasks().isEmpty())
            assertFalse(glance.wasNotified)
            assertFalse(alarm.wasInvoked)
        }
    }

    @Test
    @Story("Добавление задачи без срока выполнения")
    fun noDue_inserts_updatesGlance_noAlarm() = runTest {
        val use = newUse()

        stepSuspend("Добавление задачи без срока выполнения") {
            use(task(id = 15, title = "Прочитать книгу"))
        }
        stepSuspend("Проверка сохранения и уведомления glance") {
            assertEquals(1, repo.findAllTasks().size)
            assertTrue(glance.wasNotified)
            assertFalse(alarm.wasInvoked)
        }
    }

    @Test
    @Story("Добавление задачи со сроком выполнения")
    fun withDue_inserts_and_callsUpdateAlarm() = runTest {
        val use = newUse()
        val due = LocalDateTime(2025, 1, 1, 9, 0)
        val id = 77L
        val t = task(id = id, title = "Сходить в спортзал", due = due)

        stepSuspend("Добавление задачи со сроком выполнения") { use(t) }
        stepSuspend("Проверка, что задача сохранилась и glance обновлён") {
            assertEquals(1, repo.findAllTasks().size)
            assertTrue(glance.wasNotified)
        }
        stepSuspend("Проверка вызова alarm с корректной задачей") {
            assertTrue(alarm.wasInvoked)
            assertTrue(alarm.wasInvokedForId(id))
            val last = alarm.requireLastTask()
            assertEquals(id, last.id)
            assertEquals(due, last.dueDate)
        }
    }

    @Test
    @Story("Поиск задач по id")
    fun lookup_usesIdFromInsert() = runTest {
        val use = newUse()

        stepSuspend("Добавление две задачи") {
            use(task(id = 1, title = "Первая"))
            use(task(id = 2, title = "Вторая"))
        }
        stepSuspend("Проверка поиска по id") {
            assertNotNull(repo.findTaskById(1))
            assertNotNull(repo.findTaskById(2))
        }
    }

    @Test
    @Story("Glance может быть null")
    fun nullGlance_doesNotCrash_andInserts() = runTest {
        val use = newUse(glanceOverride = null)

        stepSuspend("Добавление задачи при glance=null") {
            use(task(id = 5, title = "Без виджета"))
        }
        stepSuspend("Проверка сохранения без падения") {
            assertEquals(1, repo.findAllTasks().size)
            assertFalse(alarm.wasInvoked)
        }
    }

    @Test
    @Story("Добавление нескольких задач подряд")
    fun multipleTasks_allInserted() = runTest {
        val use = newUse()

        stepSuspend("Добавление три задачи подряд") {
            use(task(id = 10, title = "Одна"))
            use(task(id = 11, title = "Две"))
            use(task(id = 12, title = "Три"))
        }
        stepSuspend("Проверка, что все три задачи сохранились") {
            assertEquals(3, repo.findAllTasks().size)
        }
    }

    @Test
    @Story("Задача по умолчанию имеет completed=false")
    fun defaultCompleted_isFalse() = runTest {
        val use = newUse()

        stepSuspend("Добавление задачи без поля completed") {
            use(task(id = 20, title = "Новая задача"))
        }
        stepSuspend("Проверка, что completed=false") {
            val saved = repo.findAllTasks().first()
            assertFalse(saved.completed)
        }
    }

    @Test
    @Story("Сохранение description/category/completed")
    fun insert_preservesDescriptionCategoryAndCompleted() = runTest {
        val use = newUse()

        val id = 30L
        val t = task(id = id, title = "Задача с полями", completed = true).copy(
            description = "описание",
            categoryId = 7L
        )

        stepSuspend("Добавление задачи с description и categoryId") { use(t) }
        stepSuspend("Проверка сохранения всех полей") {
            val saved = requireNotNull(repo.findTaskById(id))
            assertEquals("Задача с полями", saved.title)
            assertEquals("описание", saved.description)
            assertEquals(7L, saved.categoryId)
            assertTrue(saved.completed)
            assertNull(saved.dueDate)
            assertFalse(alarm.wasInvoked)
        }
    }
    @Test
    @Story("Перед тестами репозиторий пустой")
    fun repositoryInitiallyEmpty() = runTest {
        stepSuspend("Проверка, что список задач пустой") {
            assertTrue(repo.findAllTasks().isEmpty())
        }
    }
}
