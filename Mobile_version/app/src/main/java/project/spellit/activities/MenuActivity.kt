package project.spellit.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.viewmodels.MenuActivityModelView


class MenuActivity : AppCompatActivity() {

    private lateinit var viewModel: MenuActivityModelView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        viewModel = ViewModelProvider(this).get(MenuActivityModelView::class.java)
    }
}