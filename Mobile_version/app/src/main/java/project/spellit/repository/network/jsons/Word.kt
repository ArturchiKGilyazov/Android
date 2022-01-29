package project.spellit.repository.network.jsons

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Word {
    @SerializedName("wordId")
    @Expose
    private var wordId: Int? = null

    @SerializedName("wordName")
    @Expose
    private var wordName: String? = null

    @SerializedName("numOfRepeating")
    @Expose
    private var numOfRepeating: Int? = null

    @SerializedName("learned")
    @Expose
    private var learned: Boolean? = null

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

    fun getNumOfRepeating(): Int? {
        return numOfRepeating
    }

    fun setNumOfRepeating(numOfRepeating: Int?) {
        this.numOfRepeating = numOfRepeating
    }

    fun getLearned(): Boolean? {
        return learned
    }

    fun setLearned(learned: Boolean?) {
        this.learned = learned
    }
}