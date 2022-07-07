package com.picpay.desafio.android

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.domain.Result
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.PicPayUseCase
import com.picpay.desafio.android.domain.usecase.PicPayUseCaseImpl
import com.picpay.desafio.android.ui.MainActivity
import com.picpay.desafio.android.ui.viewmodel.PicPayViewModel
import com.picpay.desafio.android.ui.viewmodel.UserUIState
import io.mockk.mockk
import kotlinx.coroutines.Delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : KoinTest {

    @get:Rule
    var intentRule = IntentsTestRule(MainActivity::class.java, true, false)

    lateinit var mockModule: Module
    lateinit var viewModel: PicPayViewModel

    @Before
    fun setup() {
        viewModel = Mockito.mock(PicPayViewModel::class.java)
        mockModule = module {
            single(override = true) { viewModel }
        }
        loadKoinModules(mockModule)
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockModule)
    }

    @Test
    fun display_contacts_in_success_response(): Unit = runBlocking {
        val resultExpected = MutableStateFlow(
            UserUIState(
                list = listOf(
                    User(
                        id = 1,
                        img = "test",
                        name = "test",
                        username = "test"
                    )
                )
            )
        )

        `when`(viewModel.user).thenReturn(resultExpected)

        intentRule.launchActivity(null)
        Thread.sleep(10000)

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

    }

}