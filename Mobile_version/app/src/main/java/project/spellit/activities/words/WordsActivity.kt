package project.spellit.activities.words

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.activities.MainActivity
import project.spellit.activities.SERVER_URL
import project.spellit.network.JsonPlaceHolderApi
import project.spellit.network.jsons.GetCategoriesByUserId
import project.spellit.network.jsons.Words
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val CATEGORY_ID = "categoryID"

const val WORD_ID = "wordId"
const val WORD_NAME = "wordName"
const val NUM_OF_REPEATING = "numOfRepeating"
const val LEARNED = "learned"

class WordsActivity : AppCompatActivity() {

    private lateinit var wordsRecyclerView: RecyclerView
    private lateinit var category: String
    private val wordsList = ArrayList<Word>()
    private lateinit var addWordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)

        addWordButton = findViewById(R.id.add_new_word)

        val arguments = intent.extras
        category = arguments?.get(CATEGORY_ID).toString()
        //val learned = arguments?.get(LEARNED) as Boolean
        Log.d("Words Activity", "Argument: $category")

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        wordsRecyclerView = findViewById(R.id.words_recycler_view)
        wordsRecyclerView.layoutManager = linearLayoutManager

        addWordButton.setOnClickListener {
            val intent = Intent(this, AddWord::class.java)
            intent.putExtra(CATEGORY_ID, category)
            startActivity(intent)
        }

        val key = MainActivity.session?.getToken()
        if (key == "") Log.d("Category Activity", "error: we have no token")

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(
            Interceptor {
                val request =
                    it.request().newBuilder().addHeader("Authorization", "Bearer_$key").build()
                return@Interceptor it.proceed(request)
            }
        )

        val words = ArrayList<String?>()

        val adapter = WordsAdapter(words, object : WordsClickListener {
            override fun onClicked(word: String) {
                Log.d("Words Activity", "Clicked $word")
                val intent = Intent(this@WordsActivity, WordLearning::class.java)
                for (item in wordsList) {
                    if (item.wordName == word) {
                        intent.putExtra(WORD_ID, item.wordId)
                        intent.putExtra(WORD_NAME, item.wordName)
                        intent.putExtra(NUM_OF_REPEATING, item.numOfRepeating)
                        intent.putExtra(LEARNED, item.learned)
                    }
                }
                startActivity(intent)
            }
        })
        wordsRecyclerView.adapter = adapter

        val retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                SERVER_URL
            ).client(httpClient.build()).build()

        retrofit.create(JsonPlaceHolderApi::class.java)
            .getWordsByCategoryId(Integer.parseInt(category))
            .enqueue(object : Callback<List<Words>> {
                override fun onResponse(
                    call: Call<List<Words>>,
                    response: Response<List<Words>>
                ) {
                    Toast.makeText(
                        this@WordsActivity,
                        R.string.register_successful,
                        Toast.LENGTH_SHORT
                    ).show()
                    for (item in response.body()!!) {
                        adapter.addWord(item.getWordName())
                        val word = Word()
                        word.wordName = item.getWordName()
                        word.wordId = item.getWordId()
                        word.numOfRepeating = item.getNumOfRepeating() as Int?
                        word.learned = item.getLearned() as Boolean
                        wordsList.add(word)
                    }
                }

                override fun onFailure(call: Call<List<Words>>, t: Throwable) {
                    Log.d("Categories Activity", "\n\n\n\n\n error \n\n\n")
                    println(t.message)
                }
            })
    }
}
