package br.edu.unisep.excuser.domain.mapper

import br.edu.unisep.excuser.domain.mocks.mockedExcuse
import br.edu.unisep.excuser.domain.mocks.mockedExcusesDto
import junit.framework.Assert.assertEquals
import org.junit.Test

class ExcuseMapperTest {

    private val mapper: ExcuseMapper = ExcuseMapper()

    @Test
    fun `when returns ExcuseDto successfully`() {
        // When
        val result = mapper.map(mockedExcuse)

        // Then
        assertEquals(result, mockedExcusesDto)
    }
}