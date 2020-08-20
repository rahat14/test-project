package dev.syntext_error.testproject.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class QustionModel(
    @SerializedName("question")
    val question: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("options")
    val options: String = "" ,
    @SerializedName("required")
    val required: Boolean = true

) : Serializable