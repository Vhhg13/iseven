package com.example.iseven.data.datasrc.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iseven.data.model.Evenness

@Database(entities = [Evenness::class], version = 1)
abstract class KnownNumbersDB: RoomDatabase(){
    abstract fun knownNumberDao(): KnownNumberDao
}