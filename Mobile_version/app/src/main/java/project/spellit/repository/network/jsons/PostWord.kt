package project.spellit.repository.network.jsons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostWord {

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