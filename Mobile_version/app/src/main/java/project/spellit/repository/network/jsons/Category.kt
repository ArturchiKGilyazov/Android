package project.spellit.repository.network.jsons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("categoryId")
    @Expose
    var categoryId: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("words")
    @Expose
    var words: List<Any>? = null

    @SerializedName("isDefault")
    @Expose
    var isDefault: Boolean? = null
}
