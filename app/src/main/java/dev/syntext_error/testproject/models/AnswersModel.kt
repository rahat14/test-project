package dev.syntext_error.testproject.models

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "answer_table")
data class AnswersModel (

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "checkbox")
    var checkbox: String,
    @ColumnInfo(name = "text")
    var text: String,

    @ColumnInfo(name = "mcq")
    var mcq: String,

    @ColumnInfo(name = "number")
    var number: String,

    @ColumnInfo(name = "dropdown")
    var dropdown: String,

    @ColumnInfo(name = "timestamp")
    var timeStamp: String

) : Serializable