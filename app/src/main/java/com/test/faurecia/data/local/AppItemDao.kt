package com.test.faurecia.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.faurecia.data.local.model.App
import kotlinx.coroutines.flow.Flow

// DAO for accessing AppItem data in the database
@Dao
interface AppItemDao {
    // Query to get an app item by its ID
    @Query("SELECT * FROM App WHERE id = :id")
    suspend fun getAppById(id: String): App

    // Query to get the list of all app items
    @Query("SELECT * FROM App")
    fun getAppList(): Flow<List<App>>

    // Insert a list of app items into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(appItems: List<App>)
}
