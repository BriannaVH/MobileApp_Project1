package com.group3.project4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Course::class, TrivialQuestion::class], version = 2, exportSchema = false)
abstract class TrivialQuestionDatabase: RoomDatabase() {

    abstract fun trivialQuestionDao(): TrivialQuestionDao

    companion object {

        private var INSTANCE: TrivialQuestionDatabase? = null

        fun getInstance(context: Context): TrivialQuestionDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TrivialQuestionDatabase::class.java,
                        "trivial_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}