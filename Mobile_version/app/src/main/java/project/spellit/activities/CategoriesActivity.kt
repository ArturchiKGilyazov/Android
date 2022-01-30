package project.spellit.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.spellit.R
import project.spellit.activities.*
import project.spellit.activities.categories.CategoriesAdapter
import project.spellit.activities.categories.CategoriesClickListener
import project.spellit.viewmodels.CategoriesViewModel
import java.util.*
import kotlin.collections.ArrayList


class CategoriesActivity : AppCompatActivity() {

    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var addNewCategoryButton: Button
    private lateinit var categoriesWithId: ArrayList<Pair<Int?, String?>>
    private lateinit var viewModel: CategoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        categoriesRecyclerView = findViewById(R.id.categories_recycler_view)
        categoriesRecyclerView.layoutManager = linearLayoutManager

        addNewCategoryButton = findViewById(R.id.add_new_category)
        addNewCategoryButton.setOnClickListener {
            startActivity(Intent(this, AddCategoryActivity::class.java))
        }



        val key = MainActivity.session?.getToken()
        if (key == "") Log.d("Category Activity", "error: we have no token")

        val categories: ArrayList<String?> = ArrayList()
        categoriesWithId = ArrayList()

        //Нельзя впихнуть это всё во вью модель, потому что нельзя создавать активити из вьюмодели
        val adapter = CategoriesAdapter(categories, object : CategoriesClickListener {
            override fun onClicked(category: String?) {
                Log.d("Categories Activity", "Clicked $category")
                val intent = Intent(this@CategoriesActivity, WordsActivity::class.java)
                intent.putExtra(CATEGORY_ID, viewModel.returnCategoryList(category, categoriesWithId))
                startActivity(intent)
            }
        })


        categoriesRecyclerView.adapter = adapter

        viewModel.reqCategory(adapter, categoriesWithId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.fragment_category_list, menu)
        return super.onCreateOptionsMenu(menu)
    }
}