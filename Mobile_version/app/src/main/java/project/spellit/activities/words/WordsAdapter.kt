package project.spellit.activities.words

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.spellit.R

class WordsAdapter(
    private val words: ArrayList<String?>,
    private val wordsClickListener: WordsClickListener
) :
    RecyclerView.Adapter<WordsAdapter.WordsViewHolder>() {

    inner class WordsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordsTextView: TextView =
            view.findViewById(R.id.words_item) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.words_item, parent, false)
        return WordsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.wordsTextView.text = words[position]
        holder.wordsTextView.setOnClickListener {
            words[position]?.let { it1 -> wordsClickListener.onClicked(it1) }
        }
    }

    override fun getItemCount(): Int {
        return words.size
    }

    fun addWord(word: String?) {
        words.add(word)
        notifyItemInserted(words.size)
    }
}