package project.spellit.viewmodels

import android.app.Application
import android.database.sqlite.SQLiteException
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import project.spellit.activities.MainActivity
import project.spellit.repository.Repository
import java.util.*

class WordLearningViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {


    private lateinit var mTTS: TextToSpeech

    fun rightWord(wordId: Int) {
        Toast.makeText(getApplication(), "Поздравляю. Слово введено правильно", Toast.LENGTH_SHORT)
            .show()

        try {
            Repository.db?.wordDAO()?.getWordById(wordId)
        } catch (e: SQLiteException){
            print("Get Word Failed")
        }
    }

    fun wrongWord(){
        Toast.makeText(getApplication(), "Вы ошиблись :(", Toast.LENGTH_SHORT).show()
    }

    fun speak(char: CharSequence) {
        mTTS = TextToSpeech(getApplication(), this)
        mTTS.speak(char, TextToSpeech.QUEUE_FLUSH,null, null)

    }


    override fun onInit(p0: Int) {
        if (p0.equals(TextToSpeech.SUCCESS)) {
            val locale = Locale("ru")
            val result = mTTS.setLanguage(locale)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Извините, этот язык не поддерживается")
            }
        } else {
            Log.e("TTS", "Ошибка!")
        }
    }
}