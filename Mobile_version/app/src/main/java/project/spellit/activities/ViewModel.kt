package project.spellit.activities

import android.content.Context
import android.view.View
import android.widget.Toast
import project.spellit.R
import project.spellit.network.JavaNetworkService
import project.spellit.network.jsons.Session
import project.spellit.network.jsons.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel(context: Context?) : View(context) {
    fun login(username: String, password: String) {
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
                    MainActivity.session = Session
                    response.body()?.getUsername()?.let { MainActivity.session?.setUsername(it) }
                    response.body()?.getToken()?.let { MainActivity.session?.setToken(it) }
                    response.body()?.let { MainActivity.session?.setUserId(it.getUserId()) }
                }

                override fun onFailure(call: Call<Session>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(context, R.string.register_failure, Toast.LENGTH_SHORT).show()
                    MainActivity.session = Session
                    MainActivity.session?.setUsername("")
                    MainActivity.session?.setToken("")
                }
            })
    }

    fun postData(user: User) {
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