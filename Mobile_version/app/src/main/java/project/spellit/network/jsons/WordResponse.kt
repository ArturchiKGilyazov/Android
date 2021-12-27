package project.spellit.network.jsons


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WordResponse {

    @SerializedName("wordId")
    @Expose
    var wordId: Int? = null

}