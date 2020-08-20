package dev.syntext_error.testproject.db

import android.app.Application
import dev.syntext_error.testproject.models.AnswersModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class ResponseRepository(application: Application) : CoroutineScope{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var messageDao: answerDao?

    init {
        val db = responseDatabase.getDatabase(application)
        messageDao = db?.messageDao()
    }

    fun getMessages() = messageDao?.getAnswer()

    fun setMessage(response: AnswersModel) {
            launch  { setMessageBG(response) }
    }

    private suspend fun setMessageBG(message: AnswersModel){
       withContext(Dispatchers.IO){
           messageDao?.setAnswer(message)
       }
    }

}
