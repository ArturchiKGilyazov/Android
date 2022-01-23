package project.spellit.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.activities.viewmodels.MainActivityModelView
import project.spellit.network.jsons.Session

const val SERVER_URL: String = "http://192.168.56.1"

class MainActivity : AppCompatActivity() {

    companion object {
        var session: Session? = null
        var systemTypeface: Typeface = Typeface.DEFAULT
        val retrofitWorker = RetrofitWorker()
    }


    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var viewModel: MainActivityModelView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityModelView::class.java)


        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.register_button)

        loginButton.setOnClickListener { view: View ->
            viewModel.login(username.text.toString(), password.text.toString(), this)
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

}