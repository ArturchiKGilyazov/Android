package project.spellit.viewmodels

import android.app.Application
import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.lifecycle.AndroidViewModel
import project.spellit.activities.*
import project.spellit.activities.words.Word
import project.spellit.activities.words.WordsAdapter
import project.spellit.repository.Repository

class WordsViewModel(application: Application) : AndroidViewModel(application) {
    fun makeWordlist(word: String, wordsList: ArrayList<Word>): Intent{
        val intent = Intent(getApplication(), WordLearningActivity::class.java)
        for (item in wordsList) {
            if (item.wordName == word) {
                intent.putExtra(WORD_ID, item.wordId)
                intent.putExtra(WORD_NAME, item.wordName)
                intent.putExtra(NUM_OF_REPEATING, item.numOfRepeating)
                intent.putExtra(LEARNED, item.learned)
            }
        }
        return intent
    }

    fun getWords(adapter: WordsAdapter) {
        try {
            val wordList = Repository.db?.wordDAO()?.getWords()

            for (word in wordList!!){
                adapter.addWord(word.toString())
            }

        } catch (e: SQLiteException){
            print("Getting wordList Failed")
        }
    }
}