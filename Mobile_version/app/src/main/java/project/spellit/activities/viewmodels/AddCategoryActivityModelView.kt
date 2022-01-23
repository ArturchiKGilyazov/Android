package project.spellit.activities.viewmodels

import androidx.lifecycle.ViewModel
import project.spellit.network.jsons.AddCategory

class AddCategoryActivityModelView: ViewModel() {
    fun addCategory(categoryName: String): AddCategory {
        val addCategory = AddCategory()
        addCategory.setWordName(categoryName)
        addCategory.setIsDefault(false)
        return addCategory
    }

}