package project.spellit.viewmodels

import android.database.sqlite.SQLiteAbortException
import android.util.Log
import androidx.lifecycle.ViewModel
import project.spellit.activities.AddWordActivity
import project.spellit.activities.MainActivity
import project.spellit.repository.DBHelper
import project.spellit.repository.network.jsons.AddWord

class AddWordModelView: ViewModel() {
    fun addWord(wordEditText: String, addWord: AddWordActivity): AddWord {

        val dbhealper = DBHelper(addWord, null, 1)
        val word = AddWord()
        word.setWordName(wordEditText)

        try {
            dbhealper.insertWord(word.getWordName())
        }
        catch (e: SQLiteAbortException){
            Log.e("TriggerERROR", e.toString());
        }


        //TODO сделать это всё через БД
        MainActivity.retrofitWorker.reqvestAddWord(category, word, this@AddWordActivity)

        return word
    }
}