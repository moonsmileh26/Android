package com.example.android.quotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.quotes.data.database.dao.QuoteDao
import com.example.android.quotes.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
}