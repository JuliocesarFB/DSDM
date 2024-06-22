package br.com.unisep.myalbum.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    val name: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}