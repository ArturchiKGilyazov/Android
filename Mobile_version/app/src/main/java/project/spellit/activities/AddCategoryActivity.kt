package project.spellit.activities

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.activities.fragments.AddCategoryFragmentButton
import project.spellit.activities.fragments.AddCategoryFragmentEdit
import project.spellit.viewmodels.AddCategoryActivityModelView

class AddCategoryActivity : AppCompatActivity() {


    private lateinit var addCategoryButton: Button
    private lateinit var categoryNameEditText: EditText
    private lateinit var viewModel: AddCategoryActivityModelView
    var addCategoryFragmentButton = AddCategoryFragmentButton()
    var addCategoryFragmentEdit = AddCategoryFragmentEdit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        viewModel = ViewModelProvider(this)[AddCategoryActivityModelView::class.java]

    }
}