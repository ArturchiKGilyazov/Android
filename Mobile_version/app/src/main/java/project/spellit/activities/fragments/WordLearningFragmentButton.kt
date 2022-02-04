package project.spellit.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import project.spellit.R
import project.spellit.viewmodels.WordLearningViewModel

class WordLearningFragmentButton: Fragment() {


    private  var wordEditText: EditText? = null
    private  var checkButton: Button? = null
    private  var playButton: Button? = null
    private lateinit var viewModel: WordLearningViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_word_learning_button, container, false)
    }
}