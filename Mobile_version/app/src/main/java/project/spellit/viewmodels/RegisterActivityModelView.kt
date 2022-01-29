package project.spellit.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import project.spellit.R
import project.spellit.activities.MainActivity
import project.spellit.activities.RegisterActivity
import project.spellit.repository.network.JavaNetworkService
import project.spellit.repository.network.jsons.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivityModelView(application: Application) : AndroidViewModel(application) {

    fun postData(login: String, password: String) {
        val user = User()
        user.setUsername(login)
        user.setPassword(password)
        Log.d("New user:", "\n\n\n ${user.getUsername()}\n${user.getPassword()}\n\n\n")
        MainActivity.retrofitWorker.postData(user, getApplication())
    }

    fun registrationFailed() {
        Toast.makeText(getApplication(),
            R.string.register_failure_repeating_password,
            Toast.LENGTH_SHORT
        ).show()    }
}