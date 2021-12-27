package project.spellit.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import project.spellit.R
import project.spellit.network.JavaNetworkService
import project.spellit.network.jsons.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
                postData(this, user)
            } else {
                Toast.makeText(
                    this,
                    R.string.register_failure_repeating_password,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun postData(context: Context, user: User) {
        JavaNetworkService
            .getInstance()
            .jsonApi
            .postData(user)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Toast.makeText(context, R.string.register_successful, Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(context, R.string.register_failure, Toast.LENGTH_SHORT).show()
                }
            })
    }
}