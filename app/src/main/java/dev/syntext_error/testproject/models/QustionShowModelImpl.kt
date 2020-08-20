package dev.syntext_error.testproject.models

import android.content.Context
import dev.syntext_error.testproject.common.RequestCompleteListener
import dev.syntext_error.testproject.network.ApiInterface
import dev.syntext_error.testproject.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QustionShowModelImpl(private  val  context :  Context): QustionShowModel {


    override fun getQustionList(callback: RequestCompleteListener<List<QustionModel>>) {
        val apiInterface: ApiInterface = RetrofitClient.client.create(ApiInterface::class.java)
        val call: Call<List<QustionModel>> = apiInterface.callApiForData(getHeaderMap())

        // if retrofit network call success, this method will be triggered
        call.enqueue(object : Callback<List<QustionModel>>
        {
            // if retrofit network call success, this method will be triggered
            override fun onResponse(call: Call<List<QustionModel>>, response: Response<List<QustionModel>>) {
                if (response.body() != null && response.code()==200)
                {
                    callback.onRequestSuccess(response.body()!!) //let presenter know the  information data
                }
                else
                    callback.onRequestFailed(response.message()) //let presenter know about failure
            }

            // this method will be triggered if network call failed
            override fun onFailure(call: Call<List<QustionModel>>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!) //let presenter know about failure
            }
        })
    }

    private fun getHeaderMap(): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["timestamp"] = System.currentTimeMillis().toString()
        return headerMap
    }

}