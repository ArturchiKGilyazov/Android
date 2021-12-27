package project.spellit.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.network.JsonPlaceHolderApi
import project.spellit.network.jsons.AddCategory
import project.spellit.network.jsons.WordResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddCategoryActivity : AppCompatActivity() {

    private lateinit var addCategoryButton: Button
    private lateinit var categoryNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        addCategoryButton = findViewById(R.id.add_new_category_button)
        categoryNameEditText = findViewById(R.id.name_category_edit_text)

        addCategoryButton.setOnClickListener {
            val key = MainActivity.session?.getToken()
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(
                Interceptor {
                    val request =
                        it.request().newBuilder().addHeader("Authorization", "Bearer_$key").build()
                    return@Interceptor it.proceed(request)
                }
            )
            val retrofit =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                    SERVER_URL
                ).client(httpClient.build()).build()

            val addCategory = AddCategory()
            addCategory.setWordName(categoryNameEditText.text.toString())
            addCategory.setIsDefault(false)
            retrofit.create(JsonPlaceHolderApi::class.java).addCategory(
                Integer.parseInt(
                    MainActivity.session?.getUserId()
                ), addCategory
            ).enqueue(object :
                Callback<WordResponse> {
                override fun onResponse(
                    call: Call<WordResponse>,
                    response: Response<WordResponse>
                ) {
                    Toast.makeText(
                        this@AddCategoryActivity,
                        "Категория добавлена",
                        Toast.LENGTH_SHORT
                    ).show()
                    println("ID of new word:${response.body().toString()})")
                }

                override fun onFailure(call: Call<WordResponse>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(
                        this@AddCategoryActivity,
                        "Ошибка: Категория не добавлена",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }
            })
        }
    }
}