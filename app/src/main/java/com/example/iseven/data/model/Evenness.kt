package com.example.iseven.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "known_numbers")
data class Evenness(@PrimaryKey val number: Int, val isEven: Boolean, val ad: String)