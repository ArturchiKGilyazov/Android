package project.spellit.repository

import android.util.Log
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import project.spellit.R
import project.spellit.activities.*
import project.spellit.activities.categories.CategoriesAdapter
import project.spellit.activities.words.Word
import project.spellit.activities.words.WordsAdapter
import project.spellit.repository.network.JavaNetworkService
import project.spellit.repository.network.JsonPlaceHolderApi
import project.spellit.repository.network.jsons.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitWorker{

    val key = MainActivity.session?.getToken()
    val httpClient = OkHttpClient.Builder().addInterceptor(
        Interceptor {
            val request =
                it.request().newBuilder().addHeader("Authorization", "Bearer_$key").build()
            return@Interceptor it.proceed(request)
        }
    )

    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
        SERVER_URL
    ).client(httpClient.build()).build()

    fun login(username: String, password: String, mainActivity: MainActivity) {
        val user = User()
        user.setUsername(username)
        user.setPassword(password)

        JavaNetworkService
            .getInstance()
            .jsonApi
            .login(user)
            .enqueue(object : Callback<Session> {
                override fun onResponse(call: Call<Session>, response: Response<Session>) {
                    Toast.makeText(mainActivity, R.string.register_successful, Toast.LENGTH_SHORT).show()
                    MainActivity.session = Session
                    response.body()?.getUsername()?.let { MainActivity.session?.setUsername(it) }
                    response.body()?.getToken()?.let { MainActivity.session?.setToken(it) }
                    response.body()?.let { MainActivity.session?.setUserId(it.getUserId()) }
                }

                override fun onFailure(call: Call<Session>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(mainActivity, R.string.register_failure, Toast.LENGTH_SHORT).show()
                    MainActivity.session = Session
                    MainActivity.session?.setUsername("")
                    MainActivity.session?.setToken("")
                }
            })
    }

    fun postData(user: User, registerActivity: RegisterActivity, ) {
        JavaNetworkService
            .getInstance()
            .jsonApi
            .postData(user)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Toast.makeText(registerActivity, R.string.register_successful, Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(registerActivity, R.string.register_failure, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun reqvestAddCategory(
        addCategory: AddCategory,
        addCategoryActivity: AddCategoryActivity
    ) {
        retrofit.create(JsonPlaceHolderApi::class.java).addCategory(
            Integer.parseInt(
                MainActivity.session!!.getUserId()
            ), addCategory
        ).enqueue(object :
            Callback<WordResponse> {
            override fun onResponse(
                call: Call<WordResponse>,
                response: Response<WordResponse>
            ) {
                Toast.makeText(
                    addCategoryActivity,
                    "Категория добавлена",
                    Toast.LENGTH_SHORT
                ).show()
                println("ID of new word:${response.body().toString()})")
            }

            override fun onFailure(call: Call<WordResponse>, t: Throwable) {
                println(t.message)
                Toast.makeText(
                    addCategoryActivity,
                    "Ошибка: Категория не добавлена",
                    Toast.LENGTH_SHORT
                )
                    .show()

            }
        })
    }

    fun  reqvestAddWord(
        category: Int,
        word: AddWord,
        addWord: AddWordActivity
    ) {

        retrofit.create(JsonPlaceHolderApi::class.java).addWord(
            category, word
        ).enqueue(object :
            Callback<WordResponse> {
            override fun onResponse(
                call: Call<WordResponse>,
                response: Response<WordResponse>
            ) {
                Toast.makeText(addWord, "Слово добавлено", Toast.LENGTH_SHORT).show()
                println("ID of new word:${response.body().toString()})")
            }

            override fun onFailure(call: Call<WordResponse>, t: Throwable) {
                println(t.message)
                Toast.makeText(addWord, "Ошибка: Слово не добавлено", Toast.LENGTH_SHORT)
                    .show()

            }
        })
    }

    fun reqvestCategory(
        categoriesActivity: CategoriesActivity,
        adapter: CategoriesAdapter,
        categoriesWithId: ArrayList<Pair<Int?, String?>>
    ) {
        retrofit.create(JsonPlaceHolderApi::class.java).getCategoriesByUserId(
            Integer.parseInt(
                MainActivity.session!!.getUserId()
            )
        )
            .enqueue(object : Callback<List<GetCategoriesByUserId>> {
                override fun onResponse(
                    call: Call<List<GetCategoriesByUserId>>,
                    response: Response<List<GetCategoriesByUserId>>
                ) {
                    Toast.makeText(
                        categoriesActivity,
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

    fun reqvestLearningWord(
        wordId: Int,
        wordLearning: WordLearningActivity
    ) {
        retrofit.create(JsonPlaceHolderApi::class.java)
            .inputWordCorrect(wordId, Integer.parseInt(MainActivity.session!!.getUserId()))
            .enqueue(object : Callback<InputCorrectPojo> {
                override fun onResponse(
                    call: Call<InputCorrectPojo>,
                    response: Response<InputCorrectPojo>
                ) {
                    println("DEBUG")
                    println("Correct: ${response.body()?.getLearned()}")

                }

                override fun onFailure(call: Call<InputCorrectPojo>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(
                        wordLearning,
                        R.string.register_failure,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    fun reqvestWord(
        category: String,
        adapter: WordsAdapter,
        wordsList: ArrayList<Word>,
        wordsActivity: WordsActivity,
        ){
        retrofit.create(JsonPlaceHolderApi::class.java)
            .getWordsByCategoryId(Integer.parseInt(category))
            .enqueue(object : Callback<List<Words>> {
                override fun onResponse(
                    call: Call<List<Words>>,
                    response: Response<List<Words>>
                ) {
                    Toast.makeText(
                        wordsActivity,
                        R.string.register_successful,
                        Toast.LENGTH_SHORT
                    ).show()
                    for (item in response.body()!!) {
                        adapter.addWord(item.getWordName())
                        val word = Word()
                        word.wordName = item.getWordName()
                        word.wordId = item.getWordId()
                        word.numOfRepeating = item.getNumOfRepeating()
                        word.learned = item.getLearned() as Boolean
                        wordsList.add(word)
                    }
                }

                override fun onFailure(call: Call<List<Words>>, t: Throwable) {
                    Log.d("Categories Activity", "\n\n\n\n\n error \n\n\n")
                    println(t.message)
                }
            })

    }

}