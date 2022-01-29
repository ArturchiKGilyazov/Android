package project.spellit.repository.network.jsons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GetCategoriesByUserId {

    @SerializedName("categoryId")
    @Expose
    private var categoryId: Int? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("words")
    @Expose
    private var words: List<Word?>? = null

    @SerializedName("isDefault")
    @Expose
    private var isDefault: Boolean? = null

    fun getCategoryId(): Int? {
        return categoryId
    }

    fun setCategoryId(categoryId: Int?) {
        this.categoryId = categoryId
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getWords(): List<Word?>? {
        return words
    }

    fun setWords(words: List<Word?>?) {
        this.words = words
    }

    fun getIsDefault(): Boolean? {
        return isDefault
    }

    fun setIsDefault(isDefault: Boolean?) {
        this.isDefault = isDefault
    }
}