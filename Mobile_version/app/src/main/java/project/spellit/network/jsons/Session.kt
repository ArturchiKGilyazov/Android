package project.spellit.network.jsons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

object Session{


    @SerializedName("userId")
    @Expose
    private var userId: String = ""

    @SerializedName("username")
    @Expose
    private var username: String = ""

    @SerializedName("token")
    @Expose
    private var token: String = ""

    fun setUsername(username: String) {
        this.username = username
    }

    fun setToken(token: String) {
        this.token = token
    }

    fun getUsername(): String {
        return username
    }

    fun getToken(): String {
        return token
    }

    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun getUserId(): String {
        return userId
    }
}