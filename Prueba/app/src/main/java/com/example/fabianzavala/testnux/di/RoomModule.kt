/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: interface to implement the methods to provide dependencies
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.di

import android.content.Context
import com.example.fabianzavala.testnux.database.comments.CommentsDAO
import com.example.fabianzavala.testnux.database.comments.CommentsDatabase
import com.example.fabianzavala.testnux.database.posts.PostsDAO
import com.example.fabianzavala.testnux.database.posts.PostsDatabase
import com.example.fabianzavala.testnux.database.todos.TodosDAO
import com.example.fabianzavala.testnux.database.todos.TodosDatabase
import com.example.fabianzavala.testnux.database.users.UsersDAO
import com.example.fabianzavala.testnux.database.users.UsersDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun ProvideUsersDao(context: Context): UsersDAO {

        return UsersDatabase.getInstance(context).usersDAO
    }

    @Provides
    fun ProvidePostsDao(context: Context): PostsDAO {

        return PostsDatabase.getInstance(context).postsDAO
    }

    @Provides
    fun ProvideCommentsDao(context: Context): CommentsDAO {

        return CommentsDatabase.getInstance(context).commentsDAO
    }

    @Provides
    fun ProvideTodosDao(context: Context): TodosDAO {

        return TodosDatabase.getInstance(context).todosDAO
    }
}