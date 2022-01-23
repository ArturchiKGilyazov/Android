package project.spellit.activities.viewmodels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import project.spellit.R
import project.spellit.activities.RegisterActivity
import project.spellit.network.JavaNetworkService
import project.spellit.network.jsons.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivityModelView: ViewModel() {

    fun postData(user: User, registerActivity: RegisterActivity, ) {
        JavaNetworkService
            .getInstance()
            .jsonApi
            .postData(user)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Toast.makeText(registerActivity, R.string.register_successful, Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(registerActivity, R.string.register_failure, Toast.LENGTH_SHORT).show()
                }
            })
    }
}