package project.spellit.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.viewmodels.AddCategoryActivityModelView


class AddCategoryFragmentButton: Fragment() {
    private lateinit var viewModel: AddCategoryActivityModelView

    private var addCategoryButton: Button? = null
    private var categoryNameEditText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_category_button, container, false)

        addCategoryButton = view.findViewById(R.id.add_new_category_button) as? Button
        categoryNameEditText = view.findViewById(R.id.name_category_edit_text) as? EditText

        viewModel = ViewModelProvider(requireActivity()).get(AddCategoryActivityModelView::class.java)

        addCategoryButton?.setOnClickListener {
            viewModel.addCategory(categoryNameEditText?.text.toString())
        }

        return view
    }
}