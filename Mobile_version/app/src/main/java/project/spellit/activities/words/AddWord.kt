package project.spellit.activities.words

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.activities.CATEGORY_ID
import project.spellit.activities.MainActivity
import project.spellit.activities.viewmodels.AddWordModelView

class AddWord : AppCompatActivity() {

    private lateinit var addWordButton: Button
    private lateinit var wordEditText: EditText
    private lateinit var viewModel: AddWordModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)
        viewModel = ViewModelProvider(this).get(AddWordModelView::class.java)

        val category = Integer.parseInt(intent.extras?.get(CATEGORY_ID).toString())
        println(category)

        addWordButton = findViewById(R.id.add_word_button)
        wordEditText = findViewById(R.id.add_word_input_text)

        addWordButton.setOnClickListener {
            val word = viewModel.addWord(wordEditText.text.toString(), this@AddWord)

            MainActivity.retrofitWorker.reqvestAddWord(category, word, this@AddWord)
        }
    }
}