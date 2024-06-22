package br.edu.unisep.excuser.domain.dto.exception

sealed class DomainException(message: String) : Exception(message)

class GenericException(message: String): DomainException(message)
class NotFoundException(message: String): DomainException(message)