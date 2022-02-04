package project.spellit.activities.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.activities.SettingsActivity
import project.spellit.viewmodels.MenuActivityModelView

class MenuFragmentButtonSetting: Fragment() {


    private var settingsButton: Button? = null

    private lateinit var viewModel: MenuActivityModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_button_settings, container, false)

        viewModel = ViewModelProvider(this).get(MenuActivityModelView::class.java)


        settingsButton = view.findViewById(R.id.settings_button) as? Button


        settingsButton?.setOnClickListener {
            startActivity(Intent(viewModel.getApplication(), SettingsActivity::class.java))
        }

        return view
    }
}