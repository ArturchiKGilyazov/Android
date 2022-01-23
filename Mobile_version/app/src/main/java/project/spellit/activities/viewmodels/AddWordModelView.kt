package project.spellit.activities.viewmodels

import android.database.sqlite.SQLiteAbortException
import android.util.Log
import androidx.lifecycle.ViewModel
import project.spellit.activities.DBHelper
import project.spellit.network.jsons.AddWord

class AddWordModelView: ViewModel() {
    fun addWord(wordEditText: String, addWord: project.spellit.activities.words.AddWord): AddWord {

        val dbhealper = DBHelper(addWord, null, 1)
        val word = AddWord()
        word.setWordName(wordEditText)

        try {
            dbhealper.insertWord(word.getWordName())
        }
        catch (e: SQLiteAbortException){
            Log.e("TriggerERROR", e.toString());
        }
        return word
    }
}