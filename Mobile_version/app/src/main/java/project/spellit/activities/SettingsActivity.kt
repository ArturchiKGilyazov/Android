package project.spellit.activities

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import project.spellit.R

class SettingsActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private val fonts: Array<String> = arrayOf("SERIF", "ITALIC", "DEFAULT_BOLD")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)
        spinner = findViewById(R.id.spinner)

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, fonts)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        println(spinner.selectedItem)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (spinner.selectedItem.toString()) {
                    "SERIF" -> MainActivity.systemTypeface = Typeface.SERIF
                    "ITALIC" -> MainActivity.systemTypeface = Typeface.MONOSPACE
                    "DEFAULT_BOLD" -> MainActivity.systemTypeface = Typeface.DEFAULT_BOLD
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                MainActivity.systemTypeface = Typeface.DEFAULT
            }

        }
    }
}