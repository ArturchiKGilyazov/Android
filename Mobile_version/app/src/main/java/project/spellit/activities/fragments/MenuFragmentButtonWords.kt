package project.spellit.activities.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.activities.CategoriesActivity
import project.spellit.viewmodels.MenuActivityModelView

class MenuFragmentButtonWords: Fragment() {


    private var wordsButton: Button? = null

    private lateinit var viewModel: MenuActivityModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_button_words, container, false)
        viewModel = ViewModelProvider(this).get(MenuActivityModelView::class.java)

        wordsButton = view.findViewById(R.id.words_button) as? Button

        wordsButton?.setOnClickListener {
            startActivity(Intent(viewModel.getApplication(), CategoriesActivity::class.java))
        }

        return view
    }
}