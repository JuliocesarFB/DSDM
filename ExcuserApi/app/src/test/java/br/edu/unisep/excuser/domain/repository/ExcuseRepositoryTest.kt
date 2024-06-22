package br.edu.unisep.excuser.domain.repository

import br.edu.unisep.excuser.data.service.ExcuseService
import br.edu.unisep.excuser.domain.dto.base.ApiResult
import br.edu.unisep.excuser.domain.dto.excuse.ExcuseDto
import br.edu.unisep.excuser.domain.mapper.ExcuseMapper
import br.edu.unisep.excuser.domain.mocks.mockedExcuses
import br.edu.unisep.excuser.domain.repository.excuse.ExcuseRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@ExperimentalCoroutinesApi
class ExcuseRepositoryTest {

    private val service: ExcuseService = mockk()
    private val mapper: ExcuseMapper = ExcuseMapper()

    private val repository: ExcuseRepository = ExcuseRepository(mapper, service)


    @Test
    fun `when category is null, then returns success`() = runTest {
        // Given
        coEvery { service.getExcuse() } returns mockedExcuses

        // When
        val result = repository.getExcuse()

        // Then
        coVerify(exactly = 1) { service.getExcuse() }
        assertTrue { result is ApiResult.Success<ExcuseDto> }
    }

//Ficou dando erro e no console informa que esperava true, não consegui encontrar o erro
    @Test
    fun `when server result is not found, returns error`() = runTest {
        // Given
        coEvery { service.getExcuseByCategory("not-found") } throws HttpException(
            Response.error<String>(
                404,
                "".toResponseBody()
            )
        )

        // When
        val result = repository.getExcuse("not-found")

        // Then
        coVerify(exactly = 1) { service.getExcuseByCategory("not-found") }

        assertTrue { result is ApiResult.GenericError }
    }

}