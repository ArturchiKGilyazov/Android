package project.spellit.repository.network.jsons


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetAllUsers {
    @SerializedName("userId")
    @Expose
    var userId: Int? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null

    @SerializedName("numOfRepeatingToKnow")
    @Expose
    var numOfRepeatingToKnow: Any? = null
}