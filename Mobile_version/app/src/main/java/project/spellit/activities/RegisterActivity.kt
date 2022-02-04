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


class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterActivityModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(RegisterActivityModelView::class.java)

    }



}