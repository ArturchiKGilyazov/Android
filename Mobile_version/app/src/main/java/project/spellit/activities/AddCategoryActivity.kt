package project.spellit.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.activities.viewmodels.AddCategoryActivityModelView
import project.spellit.network.jsons.AddCategory

class AddCategoryActivity : AppCompatActivity() {


    private lateinit var addCategoryButton: Button
    private lateinit var categoryNameEditText: EditText
    private lateinit var viewModel: AddCategoryActivityModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        viewModel = ViewModelProvider(this).get(AddCategoryActivityModelView::class.java)


        addCategoryButton = findViewById(R.id.add_new_category_button)
        categoryNameEditText = findViewById(R.id.name_category_edit_text)

        addCategoryButton.setOnClickListener {

            val addCategory = AddCategory()
            addCategory.setWordName(categoryNameEditText.text.toString())
            addCategory.setIsDefault(false)

            MainActivity.retrofitWorker.reqvestAddCategory(addCategory, this@AddCategoryActivity)


        }
    }
}