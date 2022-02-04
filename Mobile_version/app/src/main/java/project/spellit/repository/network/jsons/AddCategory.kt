package project.spellit.repository.network.jsons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddCategory {

    @SerializedName("name")
    @Expose
    private var wordName: String = ""

    @SerializedName("isDefault")
    @Expose
    private var isDefault: Boolean = false

    fun getWordName(): String {
        return wordName
    }

    fun setWordName(wordName: String) {
        this.wordName = wordName
    }

    fun getIsDefault(): Boolean {
        return isDefault
    }

    fun setIsDefault() {
        this.isDefault = false
    }
}