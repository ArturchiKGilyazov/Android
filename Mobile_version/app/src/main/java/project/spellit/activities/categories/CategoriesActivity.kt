package project.spellit.activities.categories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.activities.AddCategoryActivity
import project.spellit.activities.MainActivity
import project.spellit.activities.SERVER_URL
import project.spellit.activities.words.CATEGORY_ID
import project.spellit.activities.words.WordsActivity
import project.spellit.network.JavaNetworkService
import project.spellit.network.JsonPlaceHolderApi
import project.spellit.network.jsons.Category
import project.spellit.network.jsons.GetAllUsers
import project.spellit.network.jsons.GetCategoriesByUserId
import project.spellit.network.jsons.Session
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CategoriesActivity : AppCompatActivity() {

    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var addNewCategoryButton: Button
    private lateinit var categoriesWithId: ArrayList<Pair<Int?, String?>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

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
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(
            Interceptor {
                val request =
                    it.request().newBuilder().addHeader("Authorization", "Bearer_$key").build()
                return@Interceptor it.proceed(request)
            }
        )

        var categories: ArrayList<String?> = ArrayList()
        categoriesWithId = ArrayList()


        val adapter = CategoriesAdapter(categories, object : CategoriesClickListener {
            override fun onClicked(category: String?) {
                Log.d("Categories Activity", "Clicked $category")
                val intent = Intent(this@CategoriesActivity, WordsActivity::class.java)
                var categoryId = 0
                for (pair in categoriesWithId) {
                    if (pair.second == category) categoryId = pair.first!!
                }
                intent.putExtra(CATEGORY_ID, categoryId)
                startActivity(intent)
            }
        })
        categoriesRecyclerView.adapter = adapter

        val retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                SERVER_URL
            ).client(httpClient.build()).build()

        retrofit.create(JsonPlaceHolderApi::class.java).getCategoriesByUserId(
            Integer.parseInt(
                MainActivity.session?.getUserId()
            )
        )
            .enqueue(object : Callback<List<GetCategoriesByUserId>> {
                override fun onResponse(
                    call: Call<List<GetCategoriesByUserId>>,
                    response: Response<List<GetCategoriesByUserId>>
                ) {
                    Toast.makeText(
                        this@CategoriesActivity,
                        R.string.register_successful,
                        Toast.LENGTH_SHORT
                    ).show()
                    for (item in response.body()!!) {
                        println("ADD CATEGORY + ${item.getName()}")
                        adapter.categoryAdd(item.getName())
                        categoriesWithId.add(Pair(item.getCategoryId(), item.getName()))
                    }
                }

                override fun onFailure(call: Call<List<GetCategoriesByUserId>>, t: Throwable) {
                    Log.d("Categories Activity", "\n\n\n\n\n error \n\n\n")
                    println(t.message)
                }
            })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.fragment_category_list, menu)
        return super.onCreateOptionsMenu(menu)
    }
}