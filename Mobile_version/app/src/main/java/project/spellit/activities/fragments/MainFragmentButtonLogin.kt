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
import project.spellit.activities.MainActivity
import project.spellit.activities.MenuActivity
import project.spellit.viewmodels.MainActivityModelView




class MainFragmentButtonLogin: Fragment() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button

    private lateinit var viewModel: MainActivityModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_button_login, container, false)
        username = EditText(view.findViewById(R.id.username))
        password = EditText(view.findViewById(R.id.password))
        loginButton = Button(view.findViewById(R.id.login_button))

        viewModel = ViewModelProvider(activity!!).get(MainActivityModelView::class.java)

        loginButton.setOnClickListener {
            viewModel.login(username.text.toString(), password.text.toString(), viewModel.getApplication())
            if (!(MainActivity.session?.getUsername().equals("")) and !(MainActivity.session?.getToken().equals("")))
                startMenuActivity()
            else
                viewModel.loginError()
        }

        return view
    }
    private fun startMenuActivity() {
        startActivity(Intent(viewModel.getApplication(), MenuActivity::class.java))
    }
}