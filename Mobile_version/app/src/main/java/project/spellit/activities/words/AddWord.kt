package project.spellit.activities.words

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.activities.MainActivity
import project.spellit.activities.SERVER_URL
import project.spellit.network.JavaNetworkService
import project.spellit.network.JsonPlaceHolderApi
import project.spellit.network.jsons.AddWord
import project.spellit.network.jsons.GetCategoriesByUserId
import project.spellit.network.jsons.Session
import project.spellit.network.jsons.WordResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddWord : AppCompatActivity() {

    private lateinit var addWordButton: Button
    private lateinit var wordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

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
            val retrofit =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                    SERVER_URL
                ).client(httpClient.build()).build()

            retrofit.create(JsonPlaceHolderApi::class.java).addWord(
                category, word
            ).enqueue(object :
                Callback<WordResponse> {
                override fun onResponse(
                    call: Call<WordResponse>,
                    response: Response<WordResponse>
                ) {
                    Toast.makeText(this@AddWord, "Слово добавлено", Toast.LENGTH_SHORT).show()
                    println("ID of new word:${response.body().toString()})")
                }

                override fun onFailure(call: Call<WordResponse>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(this@AddWord, "Ошибка: Слово не добавлено", Toast.LENGTH_SHORT)
                        .show()

                }
            })

            /*JavaNetworkService.getInstance().jsonApi.addWord(category, word).enqueue(object :
                Callback<WordResponse> {
                override fun onResponse(
                    call: Call<WordResponse>,
                    response: Response<WordResponse>
                ) {
                    Toast.makeText(this@AddWord, "Слово добавлено", Toast.LENGTH_SHORT).show()
                    println("ID of new word:${response.body().toString()})")
                }

                override fun onFailure(call: Call<WordResponse>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(this@AddWord, "Ошибка: Слово не добавлено", Toast.LENGTH_SHORT)
                        .show()

                }
            })*/
        }
    }
}