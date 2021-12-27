package project.spellit.activities

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import project.spellit.R
import project.spellit.network.JavaNetworkService
import project.spellit.network.jsons.Session
import project.spellit.network.jsons.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val SERVER_URL: String = "http://192.168.56.1"

class MainActivity : AppCompatActivity() {

    companion object {
        var session: Session? = null
        var systemTypeface: Typeface = Typeface.DEFAULT
    }

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.register_button)

        loginButton.setOnClickListener { view: View ->
            login(this, username.text.toString(), password.text.toString())
            if (!(session?.getUsername().equals("")) and !(session?.getToken().equals("")))
                startMenuActivity()
            else Toast.makeText(this, R.string.error_user_not_exist, Toast.LENGTH_SHORT).show()
        }

        registerButton.setOnClickListener { view: View ->
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun startMenuActivity() {
        startActivity(Intent(this, MenuActivity::class.java))
    }

    private fun login(context: Context, username: String, password: String) {
        val user = User()
        user.setUsername(username)
        user.setPassword(password)

        JavaNetworkService
            .getInstance()
            .jsonApi
            .login(user)
            .enqueue(object : Callback<Session> {
                override fun onResponse(call: Call<Session>, response: Response<Session>) {
                    Toast.makeText(context, R.string.register_successful, Toast.LENGTH_SHORT).show()
                    session = Session()
                    response.body()?.getUsername()?.let { session?.setUsername(it) }
                    response.body()?.getToken()?.let { session?.setToken(it) }
                    response.body()?.let { session?.setUserId(it.getUserId()) }
                }

                override fun onFailure(call: Call<Session>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(context, R.string.register_failure, Toast.LENGTH_SHORT).show()
                    session = Session()
                    session?.setUsername("")
                    session?.setToken("")
                }
            })
    }
}