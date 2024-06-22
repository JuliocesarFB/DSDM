package br.edu.unisep.excuser.domain.dto.base

import br.edu.unisep.excuser.domain.dto.exception.DomainException
import br.edu.unisep.excuser.domain.dto.exception.GenericException
import br.edu.unisep.excuser.domain.dto.exception.NotFoundException

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()

    data class GenericError(val error: GenericException) : ApiResult<Nothing>()
    data class NotFoundError(val error: NotFoundException) : ApiResult<Nothing>()

    inline fun handleResult(onSuccess: (T) -> Unit, onError: (DomainException) -> Unit) {
        when (this) {
            is Success -> onSuccess(data)
            is GenericError -> onError(error)
            is NotFoundError -> onError(error)
        }
    }
}