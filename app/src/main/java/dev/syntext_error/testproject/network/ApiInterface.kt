package dev.syntext_error.testproject.network


import dev.syntext_error.testproject.models.QustionModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap

interface ApiInterface {
    @GET("getSurvey")

    fun callApiForData(
        @HeaderMap headers: Map<String, String>
    ): Call<List<QustionModel>>
}