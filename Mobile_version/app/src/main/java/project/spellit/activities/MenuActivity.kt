package project.spellit.activities

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.viewmodels.MenuActivityModelView
import project.spellit.viewmodels.MyViewModel


//TODO Разделить всё на фрагменты
class MenuActivity : AppCompatActivity() {

    private lateinit var wordsButton: Button
    private lateinit var vocabularyButton: Button
    private lateinit var settingsButton: Button
    private lateinit var viewModel: MenuActivityModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        viewModel = ViewModelProvider(this).get(MenuActivityModelView::class.java)

        wordsButton = findViewById(R.id.words_button)
        vocabularyButton = findViewById(R.id.vocabulary_button)
        settingsButton = findViewById(R.id.settings_button)

        wordsButton.setOnClickListener {
            startActivity(Intent(this, CategoriesActivity::class.java))
        }

        vocabularyButton.setOnClickListener {
            startActivity(Intent(this, WordLearningActivity::class.java))
        }

        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}