package br.edu.unisep.excuser.domain.mocks

import br.edu.unisep.excuser.data.remote.excuse.Excuse
import br.edu.unisep.excuser.domain.dto.excuse.ExcuseDto

val mockedExcuses = listOf(
    Excuse(
        "109",
        "I got involved in an accident.",
        "office",
    )
)

val mockedExcuse: Excuse = Excuse(
    "306",
    "Due to heavy rain, the roads around my area were closed.",
    "college",
)

//Não consegui encontrar o erro, quando faço a inversão do content com o category funciona, mas do jeito que está não passa

val mockedExcusesDto = ExcuseDto(
    "Due to heavy rain, the roads around my area were closed.",
    "colegge",
)

const val mockedCategory = "category-test"