package com.example.trilogoapplication.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RequestResult::class), version = 1)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun requestResultDao(): RequestResultDao
}
