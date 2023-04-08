package com.challenge.acronyms.rest

import com.challenge.acronyms.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class AcronymRepositoryImplTest {

    private lateinit var testObject: AcronymRepository

    private val mockApi = mockk<AcronymAPI>(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        testObject = AcronymRepositoryImpl(mockApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `get acronym info when the server retrieves a list of meanings returns a SUCCESS state`() {
        // AAA

        // assigment
        coEvery { mockApi.getAcronym("HMM") } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(mockk(), mockk())
        }

        // ACTION
        val job = testScope.launch {
            testObject.getMeaning("HMM").collect {
                if (it is UIState.SUCCESS) {
                    // ASSERTION
                    assert(true)
                    assertEquals(2, it.response.size)
                }
            }
        }
        job.cancel()
    }
}