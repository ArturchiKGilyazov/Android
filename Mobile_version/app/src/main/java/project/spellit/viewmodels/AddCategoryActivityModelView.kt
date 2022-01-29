package project.spellit.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import project.spellit.activities.MainActivity
import project.spellit.repository.Repository
import project.spellit.repository.network.jsons.AddCategory

class AddCategoryActivityModelView(application: Application) : AndroidViewModel(application) {
    fun addCategory(categoryName: String): AddCategory {
        val addCategory = AddCategory()
        addCategory.setWordName(categoryName)
        addCategory.setIsDefault(false)

        MainActivity.retrofitWorker.reqvestAddCategory(addCategory, getApplication())

        return addCategory
    }

}