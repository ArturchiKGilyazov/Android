package project.spellit.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import project.spellit.R
import project.spellit.viewmodels.AddWordModelView

class AddWordFragmentButton: Fragment() {


    private var addWordButton: Button? = null
    private var wordEditText: EditText? = null

    private lateinit var viewModel: AddWordModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_word_button, container, false)

        viewModel = ViewModelProvider(this).get(AddWordModelView::class.java)


        addWordButton = view.findViewById(R.id.add_new_word_button) as? Button
        wordEditText = view.findViewById(R.id.add_word_input_text) as? EditText

        addWordButton?.setOnClickListener {
            viewModel.addWord(wordEditText?.text.toString())
        }

        return view
    }
}