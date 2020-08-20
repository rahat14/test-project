package dev.syntext_error.testproject.models


import dev.syntext_error.testproject.common.RequestCompleteListener

interface QustionShowModel {

    fun getQustionList(callback: RequestCompleteListener<List<QustionModel>>)
}