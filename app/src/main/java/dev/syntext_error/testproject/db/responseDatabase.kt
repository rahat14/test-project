package dev.syntext_error.testproject.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.syntext_error.testproject.models.AnswersModel


@Database(entities = [AnswersModel::class], version =3, exportSchema = false)
abstract class responseDatabase : RoomDatabase() {

    abstract fun messageDao(): answerDao

    companion object {

        @Volatile
        private var INSTANCE: responseDatabase? = null

        fun getDatabase(context: Context): responseDatabase? {
            if (INSTANCE == null) {
                synchronized(responseDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            responseDatabase::class.java, "response_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}