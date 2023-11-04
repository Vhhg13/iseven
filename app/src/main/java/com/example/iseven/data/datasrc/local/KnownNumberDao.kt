package com.example.iseven.data.datasrc.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.iseven.data.model.KnownNumberEntity


@Dao
interface KnownNumberDao {
    @Upsert
    suspend fun saveNumber(entity: KnownNumberEntity)

    @Query("SELECT * FROM known_numbers ORDER BY number ASC")
    suspend fun getAll(): List<KnownNumberEntity>

    @Query("SELECT * FROM known_numbers WHERE number=:n LIMIT 1")
    suspend fun check(n: Int): KnownNumberEntity

    @Query("DELETE FROM known_numbers WHERE number=:n")
    suspend fun remove(n: Int)
}