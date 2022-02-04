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
import project.spellit.activities.WordLearningActivity
import project.spellit.viewmodels.MenuActivityModelView

class MenuFragmentButtonLearning: Fragment() {


    private lateinit var viewModel: MenuActivityModelView
    private var vocabularyButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_button_learning, container, false)
        viewModel = ViewModelProvider(this).get(MenuActivityModelView::class.java)

        vocabularyButton = view.findViewById(R.id.vocabulary_button) as? Button
        vocabularyButton?.setOnClickListener {
            startActivity(Intent(viewModel.getApplication(), WordLearningActivity::class.java))
        }
        return view
    }
}