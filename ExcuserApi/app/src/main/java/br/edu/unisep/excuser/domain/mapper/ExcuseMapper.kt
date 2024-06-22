package br.edu.unisep.excuser.domain.mapper

import br.edu.unisep.excuser.data.remote.excuse.Excuse
import br.edu.unisep.excuser.domain.dto.excuse.ExcuseDto

class ExcuseMapper {

    fun map(excuse: Excuse): ExcuseDto {
        return ExcuseDto(
            excuse.category,
            excuse.excuse
        )
    }
}

