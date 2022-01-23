package project.spellit.activities.viewmodels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import project.spellit.R
import project.spellit.activities.MainActivity
import project.spellit.network.JavaNetworkService
import project.spellit.network.jsons.Session
import project.spellit.network.jsons.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityModelView: ViewModel() {
    fun login(username: String, password: String, mainActivity: MainActivity) {
        val user = User()
        user.setUsername(username)
        user.setPassword(password)

        JavaNetworkService
            .getInstance()
            .jsonApi
            .login(user)
            .enqueue(object : Callback<Session> {
                override fun onResponse(call: Call<Session>, response: Response<Session>) {
                    Toast.makeText(mainActivity, R.string.register_successful, Toast.LENGTH_SHORT).show()
                    MainActivity.session = Session
                    response.body()?.getUsername()?.let { MainActivity.session?.setUsername(it) }
                    response.body()?.getToken()?.let { MainActivity.session?.setToken(it) }
                    response.body()?.let { MainActivity.session?.setUserId(it.getUserId()) }
                }

                override fun onFailure(call: Call<Session>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(mainActivity, R.string.register_failure, Toast.LENGTH_SHORT).show()
                    MainActivity.session = Session
                    MainActivity.session?.setUsername("")
                    MainActivity.session?.setToken("")
                }
            })
    }
}