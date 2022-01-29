package project.spellit.repository.network.jsons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("username")
    @Expose
    private var username: String = ""

    @SerializedName("password")
    @Expose
    private var password: String = ""

    fun getUsername(): String {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }
}