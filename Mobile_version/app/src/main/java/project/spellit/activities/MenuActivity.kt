package project.spellit.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.activities.categories.CategoriesActivity
import project.spellit.activities.viewmodels.MyViewModel

class MenuActivity : AppCompatActivity() {

    private lateinit var wordsButton: Button
    private lateinit var vocabularyButton: Button
    private lateinit var settingsButton: Button
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        wordsButton = findViewById(R.id.words_button)
        vocabularyButton = findViewById(R.id.vocabulary_button)
        settingsButton = findViewById(R.id.settings_button)

        wordsButton.setOnClickListener {
            startActivity(Intent(this, CategoriesActivity::class.java))
        }

        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}