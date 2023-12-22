package com.test.faurecia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.faurecia.data.local.model.App

// Database class for Room
@Database(entities = [App::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appItemDao(): AppItemDao
}
