package project.spellit.viewmodels

import android.database.sqlite.SQLiteException
import androidx.lifecycle.ViewModel
import project.spellit.activities.MainActivity
import project.spellit.repository.Repository
import project.spellit.repository.database.WordEntity
import project.spellit.repository.network.jsons.AddWord

class AddWordModelView: ViewModel() {

    val repository =  MainActivity.repository

    fun addWord(wordEditText: String): AddWord {

        val word = AddWord()
        word.setWordName(wordEditText)

        val wordEntity = WordEntity(null, word.getWordName())

        try {
            Repository.db?.wordDAO()?.insertWord(wordEntity)
        } catch (e: SQLiteException){
            println("Insert Exception")
        }

        return word
    }

}