/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get and set posts data from Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.repositories

import androidx.lifecycle.LiveData
import com.example.fabianzavala.testnux.MyApplication
import com.example.fabianzavala.testnux.database.comments.CommentsDAO
import com.example.fabianzavala.testnux.database.comments.CommentsRoom
import com.example.fabianzavala.testnux.models.Comments
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentsRepository @Inject constructor(private val dao: CommentsDAO) {

    val comments: LiveData<List<CommentsRoom>> = dao.getComments(MyApplication.postId)
    private val commentScope = CoroutineScope(Job() + Dispatchers.IO)

    fun insertComments(comments: List<Comments>) {

        var commentsRoom: MutableList<CommentsRoom> = mutableListOf()

        for (comment in comments) {
            val commentRoom = CommentsRoom(
                    postId  =  comment.postId,
                    id      =  comment.id,
                    name    =  comment.name,
                    email   =  comment.email,
                    body    =  comment.body
            )
            commentsRoom.add(commentRoom)
        }

        commentScope.launch {
            dao.insertAll(commentsRoom)
        }
    }

    suspend fun updateComments(comments: List<CommentsRoom>): Int {
        return dao.updateAll(comments)
    }
}