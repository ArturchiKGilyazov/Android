package project.spellit.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import project.spellit.R
import project.spellit.activities.fragments.MainFragmentButtonLogin
import project.spellit.activities.fragments.MainFragmentButtonRegister
import project.spellit.activities.fragments.MainFragmentEditText
import project.spellit.repository.Repository
import project.spellit.repository.RetrofitWorker
import project.spellit.repository.database.WordDataBase
import project.spellit.viewmodels.MainActivityModelView
import project.spellit.repository.network.jsons.Session

const val SERVER_URL: String = "http://192.168.56.1"


class MainActivity : AppCompatActivity() {

    companion object {
        var session: Session? = null
        var systemTypeface: Typeface = Typeface.DEFAULT
        val repository = Repository()
    }

    private lateinit var viewModel: MainActivityModelView

    var fragmentManager = this.supportFragmentManager
    var fragmentTransaction = fragmentManager.beginTransaction()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityModelView::class.java]

    }

}