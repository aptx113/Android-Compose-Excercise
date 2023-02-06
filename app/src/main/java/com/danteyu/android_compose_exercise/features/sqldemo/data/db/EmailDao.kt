package com.danteyu.android_compose_exercise.features.sqldemo.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface EmailDao {
    @Query("SELECT * FROM email")
    suspend fun getAll(): List<Email>
}