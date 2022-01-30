package project.spellit.repository.database

import androidx.room.*

@Dao
interface WordDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(wordEntity: WordEntity)

    @Update()
    fun updateWord(wordEntity: WordEntity)

    @Delete
    fun deleteWord(wordEntity: WordEntity)

    @Query("SELECT * FROM wordEntity WHERE word == :word")
    fun getWordByName(word: String): List<WordEntity>

    @Query("SELECT * FROM wordEntity WHERE id == :id")
    fun getWordById(id: Int): List<WordEntity>

    @Query("SELECT * FROM wordEntity")
    fun getWords(): List<WordEntity>

}