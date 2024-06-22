package br.edu.unisep.excuser.data.remote.excuse

import com.squareup.moshi.Json

data class Excuse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "excuse") val excuse: String,
    @field:Json(name = "category") val category: String
)
