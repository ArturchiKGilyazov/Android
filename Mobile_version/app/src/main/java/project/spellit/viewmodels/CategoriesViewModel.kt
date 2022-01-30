package project.spellit.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import project.spellit.activities.categories.CategoriesAdapter
import project.spellit.repository.Repository

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {
    fun returnCategoryList(category: String?, categoriesWithId: ArrayList<Pair<Int?, String?>>): Int {
        var categoryId = 0
        for (pair in categoriesWithId) {
            if (pair.second == category) categoryId = pair.first!!
        }

        return categoryId;
    }

    fun reqCategory(adapter: CategoriesAdapter, categoriesWithId: ArrayList<Pair<Int?, String?>>) {
        Repository.retrofitWorker.reqvestCategory(getApplication(), adapter,
            categoriesWithId)

    }

}