package com.example.iseven.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.iseven.data.model.KnownListItem

@Entity(tableName = "known_numbers")
data class KnownNumberEntity(@PrimaryKey val number: Int, val parity: Boolean, val ad: String)