package com.example.iseven.data.datasrc.local

import com.example.iseven.data.model.KnownNumberEntity


class KnownNumbersDataSource(db: KnownNumbersDB){
    private val dao = db.knownNumberDao()

    suspend fun getNumbers() = dao.getAll()
    suspend fun submit(entity: KnownNumberEntity) = dao.saveNumber(entity)
    suspend fun check(n: Int) = dao.check(n)
    suspend fun remove(n: Int) = dao.remove(n)
}