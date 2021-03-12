/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to extends application
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux

import android.app.Application
import com.example.fabianzavala.testnux.di.ApplicationComponent
import com.example.fabianzavala.testnux.di.DaggerApplicationComponent

class MyApplication: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)

    companion object
    {
        var userId: Int = 1
        var postId: Int = 1
    }
}