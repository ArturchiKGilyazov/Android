package project.spellit.network

import project.spellit.network.jsons.*
import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {

    @POST("/api/users/register")
    fun postData(@Body data: User): Call<User>

    @POST("/api/users/login")
    fun login(@Body data: User): Call<Session>

    @POST("api/word/{category_id}")
    fun addWord(@Path("category_id") categoryId: Int, @Body word: AddWord): Call<WordResponse>

    @POST("/api/category/{userId}")
    fun addCategory(
        @Path("userId") userId: Int,
        @Body categoryName: AddCategory
    ): Call<WordResponse>

    @GET("/api/users")
    fun getAllUsers(): Call<List<GetAllUsers>>

    @GET("api/categories/{userId}")
    fun getCategoriesByUserId(@Path("userId") userId: Int): Call<List<GetCategoriesByUserId>>

    @GET("api/words/{categoryId}")
    fun getWordsByCategoryId(@Path("categoryId") categoryId: Int): Call<List<Words>>

    @PATCH("/api/word/right/{wordId}/{userId}")
    fun inputWordCorrect(
        @Path("wordId") wordId: Int,
        @Path("userId") userId: Int
    ): Call<InputCorrectPojo>
}