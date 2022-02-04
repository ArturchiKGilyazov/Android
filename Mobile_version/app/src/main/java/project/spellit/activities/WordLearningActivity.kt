package project.spellit.activities

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import project.spellit.R
import project.spellit.repository.Repository
import project.spellit.repository.database.WordDataBase
import project.spellit.viewmodels.WordLearningViewModel
import java.util.*

class WordLearningActivity : AppCompatActivity() {

    private lateinit var wordTextView: TextView
    private lateinit var wordEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var playButton: Button
    private lateinit var checkBox: CheckBox
    private lateinit var whatIsTheWordTextView: TextView
    private lateinit var viewModel: WordLearningViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_learning1)
        viewModel = ViewModelProvider(this).get(WordLearningViewModel::class.java)

        val wordName = intent.extras?.get(WORD_NAME).toString()
        val wordId = Integer.parseInt(intent.extras?.get(WORD_ID).toString())
        val learning = intent.extras?.get(LEARNED) as Boolean


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
            val char: CharSequence = wordTextView.text
            viewModel.speak(char)
        }

        checkButton.setOnClickListener {
            println(wordTextView.text.toString())
            println(wordEditText.text.toString())
            if (wordTextView.text.toString() == (wordEditText.text.toString())) {
                viewModel.rightWord(wordId)
            } else {
                viewModel.wrongWord()
            }
        }
    }


}