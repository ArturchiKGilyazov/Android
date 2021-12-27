package project.spellit.activities.words

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.activities.MainActivity
import project.spellit.activities.SERVER_URL
import project.spellit.network.JsonPlaceHolderApi
import project.spellit.network.jsons.InputCorrectPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        val numOfRepeating = Integer.parseInt(intent.extras?.get(NUM_OF_REPEATING).toString())
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

                val key = MainActivity.session?.getToken()
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(
                    Interceptor {
                        val request =
                            it.request().newBuilder().addHeader("Authorization", "Bearer_$key")
                                .build()
                        return@Interceptor it.proceed(request)
                    }
                )
                val retrofit =
                    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                        SERVER_URL
                    ).client(httpClient.build()).build()

                retrofit.create(JsonPlaceHolderApi::class.java)
                    .inputWordCorrect(wordId, Integer.parseInt(MainActivity.session?.getUserId()))
                    .enqueue(object : Callback<InputCorrectPojo> {
                        override fun onResponse(
                            call: Call<InputCorrectPojo>,
                            response: Response<InputCorrectPojo>
                        ) {
                            println("DEBUG")
                            println("Correct: ${response.body()?.getLearned()}")

                        }

                        override fun onFailure(call: Call<InputCorrectPojo>, t: Throwable) {
                            println(t.message)
                            Toast.makeText(
                                this@WordLearning,
                                R.string.register_failure,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
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
        if (mTTS != null) {
            mTTS.stop()
            mTTS.shutdown()
        }
        super.onDestroy()
    }
}