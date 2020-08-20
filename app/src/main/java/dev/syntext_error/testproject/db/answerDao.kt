package dev.syntext_error.testproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.syntext_error.testproject.models.AnswersModel


@Dao
interface answerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAnswer(movie: AnswersModel)

    @Query("SELECT * from answer_table ORDER BY id ASC")
    fun getAnswer(): LiveData<List<AnswersModel>>



}