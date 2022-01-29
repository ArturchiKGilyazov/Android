package project.spellit.repository.network.jsons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterPost {

    @SerializedName("userId")
    @Expose
    private var userId: Int = 0

    @SerializedName("id")
    @Expose
    private var id: Int = 0

    @SerializedName("title")
    @Expose
    private var title: String = ""

    @SerializedName("body")
    @Expose
    private var body: String = ""

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getUserId(): Int {
        return userId
    }

    fun setUserId(userId: Int) {
        this.userId = userId
    }

    fun getBody(): String {
        return body
    }

    fun setBody(body: String) {
        this.body = body
    }
}