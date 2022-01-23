package project.spellit.activities.words

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.activities.*
import java.util.*

class WordLearning : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var wordTextView: TextView
    private lateinit var wordEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var playButton: Button
    private lateinit var checkBox: CheckBox
    private lateinit var whatIsTheWordTextView: TextView

    private lateinit var mTTS: TextToSpeech


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_word_learning)

        val wordName = intent.extras?.get(WORD_NAME).toString()
        val wordId = Integer.parseInt(intent.extras?.get(WORD_ID).toString())
        val learning = intent.extras?.get(LEARNED) as Boolean


        mTTS = TextToSpeech(this, this)

        playButton = findViewById(R.id.play_button)
        wordTextView = findViewById(R.id.word_text_view)
        wordEditText = findViewById(R.id.text_input_word)
        checkButton = findViewById(R.id.check_input)
        checkBox = findViewById(R.id.learned_check_box)
        whatIsTheWordTextView = findViewById(R.id.what_is_the_word)

        checkBox.isChecked = learning

        wordTextView.text = wordName
        whatIsTheWordTextView.typeface = MainActivity.systemTypeface

        playButton.setOnClickListener {
            mTTS.speak(wordTextView.text.toString(), TextToSpeech.QUEUE_FLUSH, null)
        }

        checkButton.setOnClickListener {
            println(wordTextView.text.toString())
            println(wordEditText.text.toString())
            if (wordTextView.text.toString() == (wordEditText.text.toString())) {
                Toast.makeText(this, "Поздравляю. Слово введено правильно", Toast.LENGTH_SHORT)
                    .show()

                val retrofitWorker = RetrofitWorker()
                retrofitWorker.reqvestLearningWord(wordId, this@WordLearning)
            } else {
                Toast.makeText(this, "Вы ошиблись :(", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onInit(p0: Int) {
        if (p0 === TextToSpeech.SUCCESS) {
            val locale = Locale("ru")
            val result = mTTS.setLanguage(locale)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Извините, этот язык не поддерживается")
            } else {
                playButton.setEnabled(true)
            }
        } else {
            Log.e("TTS", "Ошибка!")
        }
    }

    override fun onDestroy() {
        ::mTTS.isInitialized
        mTTS.stop()
        mTTS.shutdown()
        super.onDestroy()
    }
}