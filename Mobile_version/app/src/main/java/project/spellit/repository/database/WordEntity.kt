package project.spellit.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val word: String
)