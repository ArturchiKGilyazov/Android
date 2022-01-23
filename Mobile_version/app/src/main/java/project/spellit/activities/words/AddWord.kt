package project.spellit.activities.words

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.activities.CATEGORY_ID
import project.spellit.activities.DBHelper
import project.spellit.activities.MainActivity
import project.spellit.activities.RetrofitWorker
import project.spellit.network.jsons.AddWord

class AddWord : AppCompatActivity() {

    private lateinit var addWordButton: Button
    private lateinit var wordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)
        val dbhealper = DBHelper(this, null, 1)

        val category = Integer.parseInt(intent.extras?.get(CATEGORY_ID).toString())
        println(category)

        addWordButton = findViewById(R.id.add_word_button)
        wordEditText = findViewById(R.id.add_word_input_text)

        addWordButton.setOnClickListener {
            val word = AddWord()
            word.setWordName(wordEditText.text.toString())

            val key = MainActivity.session?.getToken()
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(
                Interceptor {
                    val request =
                        it.request().newBuilder().addHeader("Authorization", "Bearer_$key").build()
                    return@Interceptor it.proceed(request)
                }
            )

            dbhealper.insertWord(word.getWordName())

            val retrofitWorker = RetrofitWorker()
            retrofitWorker.reqvestAddWord(httpClient, category, word, this@AddWord)
        }
    }
}