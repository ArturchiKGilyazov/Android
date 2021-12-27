package project.spellit.network.jsons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddWord {

    @SerializedName("wordName")
    @Expose
    private var wordName: String = ""

    fun getWordName(): String {
        return wordName
    }

    fun setWordName(wordName: String) {
        this.wordName = wordName
    }
}