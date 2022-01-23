package project.spellit.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.activities.viewmodels.RegisterActivityModelView
import project.spellit.network.jsons.User

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
                val user = User()
                user.setUsername(loginEditText.text.toString())
                user.setPassword(passwordEditText.text.toString())
                Log.d("New user:", "\n\n\n ${user.getUsername()}\n${user.getPassword()}\n\n\n")
                viewModel.postData(user, this)
            } else {
                Toast.makeText(
                    this,
                    R.string.register_failure_repeating_password,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }



}