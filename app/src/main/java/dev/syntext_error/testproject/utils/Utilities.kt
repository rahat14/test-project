package dev.syntext_error.testproject.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.syntext_error.testproject.models.QustionModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class Utilities {


         companion object {
               //1
               val ROOT_URL = "https://example-response.herokuapp.com/"

             public  fun saveData ( context : Context  , qusList : List<QustionModel> ){
                 val prefs: SharedPreferences = context.getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                     ?: error("err")
                 val gson = Gson()


                 val prefsEditor = prefs.edit()

                 val json = gson.toJson(qusList)
                 prefsEditor.putString("qusList", json)
                 prefsEditor.apply()
             }

             public fun fetchArrayList(context : Context): List<QustionModel> {
                 val prefs: SharedPreferences = context.getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                     ?: error("err")
                 val gson = Gson()
                 val yourArrayList: List<QustionModel>
                 val json = prefs.getString("qusList", "")

                 yourArrayList = when {
                     json.isNullOrEmpty() -> ArrayList()
                     else -> gson.fromJson(json, object : TypeToken<List<QustionModel>>() {}.type)
                 }

                 return yourArrayList
             }

             fun getDate(): String? {

                 val date = Calendar.getInstance().time
                 val formatter =  SimpleDateFormat("HH:mm aa yyyy-MM-dd") //or use getDateInstance()
                 val formatedDate = formatter.format(date)
                return formatedDate.toString()
             }

         }





}