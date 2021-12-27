package project.spellit.activities.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.spellit.R

class CategoriesAdapter(
    private val categories: ArrayList<String?>,
    private val categoriesClickListener: CategoriesClickListener
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    inner class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoriesTextView: TextView =
            view.findViewById(R.id.categories_item) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return CategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.categoriesTextView.text = categories[position]
        holder.categoriesTextView.setOnClickListener {
            categoriesClickListener.onClicked(categories[position])
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun categoryAdd(categoryName: String?) {
        categories.add(categoryName)
        notifyItemInserted(categories.size)
    }
}