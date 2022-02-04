package project.spellit.activities.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.activities.RegisterActivity
import project.spellit.viewmodels.MainActivityModelView

class MainFragmentButtonRegister: Fragment() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var registerButton: Button


    private lateinit var viewModel: MainActivityModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityModelView::class.java)

        val view = inflater.inflate(R.layout.fragment_main_button_register, container, false)

        registerButton = view.findViewById(R.id.register_button) as Button


        registerButton.setOnClickListener {
            startActivity(Intent(viewModel.getApplication(), RegisterActivity::class.java))
        }

        return view
    }
}