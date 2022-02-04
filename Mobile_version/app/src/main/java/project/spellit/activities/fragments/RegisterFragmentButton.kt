package project.spellit.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.viewmodels.MainActivityModelView
import project.spellit.viewmodels.RegisterActivityModelView

class RegisterFragmentButton: Fragment() {


    private var loginEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var repeatPasswordEditText: EditText? = null
    private var registerButton: Button? = null

    private lateinit var viewModel: RegisterActivityModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(RegisterActivityModelView::class.java)

        val view = inflater.inflate(R.layout.fragment_register_button, container, false)


        registerButton = view.findViewById(R.id.register_now_button) as? Button
        loginEditText = view.findViewById(R.id.username_register) as? EditText
        passwordEditText = view.findViewById(R.id.password_register) as? EditText
        repeatPasswordEditText = view.findViewById(R.id.repeat_password) as? EditText


        registerButton?.setOnClickListener {


            if (passwordEditText?.text.toString() == repeatPasswordEditText?.text.toString()) {
                viewModel.postData(loginEditText?.text.toString(), passwordEditText?.text.toString())
            } else {
                viewModel.registrationFailed()
            }
        }

        return view
    }
}