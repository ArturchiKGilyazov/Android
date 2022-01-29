package project.spellit.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.viewmodels.RegisterActivityModelView
import project.spellit.repository.network.jsons.User


//TODO Разделить всё на фрагменты
class RegisterActivity : AppCompatActivity() {

    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var viewModel: RegisterActivityModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(RegisterActivityModelView::class.java)


        loginEditText = findViewById(R.id.username_register)
        passwordEditText = findViewById(R.id.password_register)
        repeatPasswordEditText = findViewById(R.id.repeat_password)
        registerButton = findViewById(R.id.register_now_button)

        registerButton.setOnClickListener {
            if (passwordEditText.text.toString() == repeatPasswordEditText.text.toString()) {
                viewModel.postData(loginEditText.text.toString(), passwordEditText.text.toString())
            } else {
                viewModel.registrationFailed()
            }
        }
    }



}