package dev.syntext_error.testproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.syntext_error.testproject.common.RequestCompleteListener
import dev.syntext_error.testproject.models.QustionModel
import dev.syntext_error.testproject.models.QustionShowModel
import java.util.*

class GetQustionViewModel : ViewModel() {

    val QustionListLiveData = MutableLiveData<List<QustionModel>>()
    val progressBarLiveData = MutableLiveData<Boolean>()
    val weatherInfoFailureLiveData = MutableLiveData<String>()
    fun getQusitonList(model: QustionShowModel){

        progressBarLiveData.postValue(true)

        model.getQustionList(object : RequestCompleteListener<List<QustionModel>>{
            override fun onRequestSuccess(data: List<QustionModel>) {
                // business logic and data manipulation tasks should be done here

                progressBarLiveData.postValue(false) // PUSH data to LiveData object to hide progress bar

                // After applying business logic and data manipulation, we push data to show on UI
                QustionListLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                progressBarLiveData.postValue(false) // hide progress bar
                weatherInfoFailureLiveData.postValue(errorMessage) // PUSH error message to LiveData object
            }
        })
    }


}