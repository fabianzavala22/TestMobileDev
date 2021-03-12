/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get and set comments by repository
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.ui.comments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabianzavala.testnux.database.comments.CommentsRoom
import com.example.fabianzavala.testnux.models.Comments
import com.example.fabianzavala.testnux.repositories.CommentsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentsViewModel @Inject constructor(private val repository: CommentsRepository): ViewModel() {

    val comments: LiveData<List<CommentsRoom>> = repository.comments

    fun insertComments(comments: List<Comments>) {
        repository.insertComments(comments)
    }

    fun updateComments(comments: List<CommentsRoom>) = viewModelScope.launch{
        val newRowId = repository.updateComments(comments)
        if (newRowId > -1)
        {
            Log.e("Comments Room","UPDATED OK")
        } else
        {
            Log.e("Comments Room","Error Ocurred")
        }
    }
}