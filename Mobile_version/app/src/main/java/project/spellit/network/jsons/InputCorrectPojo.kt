package project.spellit.network.jsons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InputCorrectPojo {

    @SerializedName("wordId")
    @Expose
    private var wordId: Int? = null

    @SerializedName("wordName")
    @Expose
    private var wordName: String? = null

    @SerializedName("numOfRepeating")
    @Expose
    private var numOfRepeating: Any? = null

    @SerializedName("learned")
    @Expose
    private var learned: Any? = null

    fun getWordId(): Int? {
        return wordId
    }

    fun setWordId(wordId: Int?) {
        this.wordId = wordId
    }

    fun getWordName(): String? {
        return wordName
    }

    fun setWordName(wordName: String?) {
        this.wordName = wordName
    }

    fun getNumOfRepeating(): Any? {
        return numOfRepeating
    }

    fun setNumOfRepeating(numOfRepeating: Any?) {
        this.numOfRepeating = numOfRepeating
    }

    fun getLearned(): Any? {
        return learned
    }

    fun setLearned(learned: Any?) {
        this.learned = learned
    }
}