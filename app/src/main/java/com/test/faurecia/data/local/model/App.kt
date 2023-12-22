package com.test.faurecia.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Data class representing an app item in the database
@Entity
data class App(
    @PrimaryKey val id: String,  // Primary key for the database
    val name: String,  // Name of the app
    val icon: String,  // Icon of the app
    val banner: String?,  // Banner of the app (optional)
    val rating: Double  // Rating of the app
)
