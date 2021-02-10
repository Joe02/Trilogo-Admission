package com.example.trilogoapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "request")
data class RequestResult(

    @PrimaryKey
    @ColumnInfo(name = "results")
    val results: List<Movie>,

    @ColumnInfo(name = "pagesLength")
    @SerializedName("total_pages") val pagesLength: Int
)

