package project.spellit.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.spellit.R
import project.spellit.activities.words.*
import project.spellit.viewmodels.WordsViewModel


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
    private lateinit var viewModel: WordsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)
        viewModel = ViewModelProvider(this).get(WordsViewModel::class.java)

        addWordButton = findViewById(R.id.add_new_word)

        val arguments = intent.extras
        category = arguments?.get(CATEGORY_ID).toString()
        Log.d("Words Activity", "Argument: $category")

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        wordsRecyclerView = findViewById(R.id.words_recycler_view)
        wordsRecyclerView.layoutManager = linearLayoutManager

        addWordButton.setOnClickListener {
            val intent = Intent(this, AddWordActivity::class.java)
            intent.putExtra(CATEGORY_ID, category)
            startActivity(intent)
        }

        val key = MainActivity.session?.getToken()
        if (key == "") Log.d("Category Activity", "error: we have no token")

        
        val words = ArrayList<String?>()

        val adapter = WordsAdapter(words, object : WordsClickListener {
            override fun onClicked(word: String) {
                Log.d("Words Activity", "Clicked $word")


                startActivity(viewModel.makeWordlist(word, wordsList))
            }
        })

        wordsRecyclerView.adapter = adapter

        viewModel.getWords(adapter)
    }
}
