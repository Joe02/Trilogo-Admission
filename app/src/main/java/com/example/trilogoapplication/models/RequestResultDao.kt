package com.example.trilogoapplication.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RequestResultDao {
    @Query("SELECT * FROM request")
    fun getLastRequest(): RequestResult

    @Insert
    fun insertRequest(vararg request: RequestResult)

    @Delete
    fun deleteLastRequest(request: RequestResult)
}
