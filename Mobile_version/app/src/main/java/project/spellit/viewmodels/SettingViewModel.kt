package project.spellit.viewmodels

import android.R
import android.app.Application
import android.graphics.Typeface
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import project.spellit.activities.MainActivity

class SettingViewModel(application: Application) : AndroidViewModel(application) {


    private val fonts: Array<String> = arrayOf("SERIF", "ITALIC", "DEFAULT_BOLD")

    fun chooseShrift(spinner: Spinner) {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(getApplication(), R.layout.simple_spinner_item, fonts)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
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