package com.example.iseven.data.datasrc.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.iseven.data.model.Evenness


@Dao
interface KnownNumberDao {
    @Upsert
    suspend fun saveNumber(entity: Evenness)

    @Query("SELECT * FROM known_numbers ORDER BY number ASC")
    suspend fun getAll(): List<Evenness>

    @Query("SELECT * FROM known_numbers WHERE number=:n LIMIT 1")
    suspend fun check(n: Int): Evenness?

    @Query("DELETE FROM known_numbers WHERE number=:n")
    suspend fun remove(n: Int)
}