package br.edu.unisep.excuser.data.service

import br.edu.unisep.excuser.data.remote.excuse.Excuse
import br.edu.unisep.excuser.data.service.config.GET_RANDOM
import br.edu.unisep.excuser.data.service.config.GET_RANDOM_BY_CATEGORY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExcuseService {

    @GET(GET_RANDOM)
    suspend fun getExcuse(): List<Excuse>

    @GET(GET_RANDOM_BY_CATEGORY)
    suspend fun getExcuseByCategory(@Path("category") category: String): List<Excuse>
}