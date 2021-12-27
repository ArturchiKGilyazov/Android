package project.spellit.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import project.spellit.R
import project.spellit.activities.categories.CategoriesActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var wordsButton: Button
    private lateinit var vocabularyButton: Button
    private lateinit var settingsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

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