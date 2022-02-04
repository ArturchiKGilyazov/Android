package project.spellit.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import project.spellit.R
import project.spellit.activities.MainActivity
import project.spellit.repository.Repository
import project.spellit.repository.network.JavaNetworkService
import project.spellit.repository.network.jsons.Session
import project.spellit.repository.network.jsons.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityModelView(application: Application) : AndroidViewModel(application) {
    fun login(username: String, password: String) {
        Repository.retrofitWorker.login(username, password, getApplication())
    }

    fun loginError() {
        Toast.makeText(getApplication(), R.string.error_user_not_exist, Toast.LENGTH_SHORT).show()
    }
}