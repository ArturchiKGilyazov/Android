package project.spellit.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.room.Room
import project.spellit.activities.MainActivity
import project.spellit.repository.Repository
import project.spellit.repository.database.WordDataBase
import project.spellit.repository.network.jsons.AddCategory

class AddCategoryActivityModelView(application: Application) : AndroidViewModel(application) {
    fun addCategory(categoryName: String): AddCategory {
        Repository.db = Room.databaseBuilder(getApplication(), WordDataBase::class.java, "database").allowMainThreadQueries().build()

        val addCategory = AddCategory()
        addCategory.setWordName(categoryName)
        addCategory.setIsDefault()

        Repository.retrofitWorker.reqvestAddCategory(addCategory, getApplication())

        return addCategory
    }

}