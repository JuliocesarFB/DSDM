package br.edu.unisep.excuser.ui.excuser.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.edu.unisep.excuser.domain.dto.base.ApiResult
import br.edu.unisep.excuser.domain.mocks.mockedCategory
import br.edu.unisep.excuser.domain.mocks.mockedExcusesDto
import br.edu.unisep.excuser.domain.repository.excuse.ExcuseRepository
import br.edu.unisep.excuser.ui.excuse.viewmodel.ExcuseViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ExcuserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: ExcuseRepository = mockk()
    private val viewModel: ExcuseViewModel = ExcuseViewModel(repository)

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `when successful, returns excuse`() = runTest {
        //Given
        coEvery { repository.getExcuse(any()) } returns ApiResult.Success(mockedExcusesDto)
        viewModel.category = mockedCategory

        //When
        viewModel.getExcuse()

        advanceUntilIdle()

        //Then
        coVerify(exactly = 1) { repository.getExcuse(mockedCategory) }
        assertEquals(mockedExcusesDto, viewModel.excuse.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}