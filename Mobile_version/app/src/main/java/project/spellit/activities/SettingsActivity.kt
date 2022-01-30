package project.spellit.activities

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import project.spellit.R
import project.spellit.viewmodels.SettingViewModel


class SettingsActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var viewModel: SettingViewModel
    private val fonts: Array<String> = arrayOf("SERIF", "ITALIC", "DEFAULT_BOLD")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        spinner = findViewById(R.id.spinner)
        viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)

        viewModel.chooseShrift(spinner)

    }
}