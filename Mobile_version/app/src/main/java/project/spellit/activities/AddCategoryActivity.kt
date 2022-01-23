package project.spellit.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.network.jsons.AddCategory

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


            val addCategory = AddCategory()
            addCategory.setWordName(categoryNameEditText.text.toString())
            addCategory.setIsDefault(false)

            val retrofitWorker = RetrofitWorker()
            retrofitWorker.reqvestAddCategory(httpClient, addCategory, this@AddCategoryActivity)


        }
    }
}