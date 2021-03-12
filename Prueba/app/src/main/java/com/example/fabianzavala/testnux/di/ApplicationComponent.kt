/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: interface to implement the methods to inject dependencies
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.di

import android.content.Context
import com.example.fabianzavala.testnux.ui.comments.CommentsActivity
import com.example.fabianzavala.testnux.ui.posts.PostsActivity
import com.example.fabianzavala.testnux.ui.todos.TodosActivity
import com.example.fabianzavala.testnux.ui.users.UsersActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RoomModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: UsersActivity)
    fun inject(activity: PostsActivity)
    fun inject(activity: CommentsActivity)
    fun inject(activity: TodosActivity)
}