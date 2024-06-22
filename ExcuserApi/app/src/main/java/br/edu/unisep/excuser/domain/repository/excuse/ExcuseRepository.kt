package br.edu.unisep.excuser.domain.repository.excuse

import br.edu.unisep.excuser.data.service.ExcuseService
import br.edu.unisep.excuser.domain.dto.base.ApiResult
import br.edu.unisep.excuser.domain.dto.excuse.ExcuseDto
import br.edu.unisep.excuser.domain.dto.exception.GenericException
import br.edu.unisep.excuser.domain.dto.exception.NotFoundException
import br.edu.unisep.excuser.domain.mapper.ExcuseMapper
import retrofit2.HttpException

class ExcuseRepository(
    private val mapper: ExcuseMapper,
    private val service: ExcuseService
) {

    suspend fun getExcuse(category: String? = null): ApiResult<ExcuseDto> {
        return try {
            val excuse = if (category == null) {
                service.getExcuse().first()
            } else {
                service.getExcuseByCategory(category).first()
            }
            val result = mapper.map(excuse)

            ApiResult.Success(result)
        } catch (exception: Exception) {
            handleError(exception)
        }
    }

    private fun handleError(exception: Exception): ApiResult<Nothing> {
        if (exception is HttpException && exception.code() == 404) {
            return ApiResult.NotFoundError(NotFoundException(exception.message()))
        }

        return ApiResult.GenericError(GenericException(exception.message ?: ""))
    }
}