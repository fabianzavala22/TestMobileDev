/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to get and set API REST services
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-09     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.webservice

import com.example.fabianzavala.testnux.Users
import com.example.fabianzavala.testnux.models.Comments
import com.example.fabianzavala.testnux.models.NewTask
import com.example.fabianzavala.testnux.models.Posts
import com.example.fabianzavala.testnux.models.Todos
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersService {

    @GET("/users")
    fun getUsers(): Call<List<Users>>

    @GET("/users/{userId}/posts")
    fun getPostsByUserId(@Path("userId") userId: Int): Call<List<Posts>>

    @GET("/posts/{postId}/comments")
    fun getCommentsByPostId(@Path("postId") postId: Int): Call<List<Comments>>

    @GET("/users/{userId}/todos")
    fun getTodosByUserId(@Path("userId") userId: Int): Call<List<Todos>>

    @POST("/users/{userId}/todos")
    fun addTodosByUserId(@Path("userId") userId: Int, @Body task: NewTask ): Call<Todos>

}