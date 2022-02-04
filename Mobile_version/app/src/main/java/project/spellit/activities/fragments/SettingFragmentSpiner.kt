package project.spellit.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.viewmodels.SettingViewModel

class SettingFragmentSpiner: Fragment() {


    private var spinner: Spinner? = null
    private lateinit var viewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_setting_spiner, container, false)
        spinner = view.findViewById(R.id.spinner) as? Spinner
        viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)

        viewModel.chooseShrift(spinner)
        return view
    }
}