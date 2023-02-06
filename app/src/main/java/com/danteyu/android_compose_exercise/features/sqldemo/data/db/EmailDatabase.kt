package com.danteyu.android_compose_exercise.features.sqldemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Email::class), version = 1)
abstract class EmailDatabase : RoomDatabase() {
    abstract fun emailDao(): EmailDao

    companion object {
        @Volatile
        private var INSTANCE: EmailDatabase? = null

        fun getDatabase(context: Context): EmailDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, EmailDatabase::class.java, "email_database")
                        .createFromAsset("database/Email.db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}