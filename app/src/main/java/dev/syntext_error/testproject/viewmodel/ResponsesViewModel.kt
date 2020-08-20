package dev.syntext_error.testproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.syntext_error.testproject.db.ResponseRepository
import dev.syntext_error.testproject.models.AnswersModel


class ResponsesViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: ResponseRepository = ResponseRepository(application)

    fun getMessages() = repository.getMessages()

    fun setMessage(response: AnswersModel) { repository.setMessage(response)}

}