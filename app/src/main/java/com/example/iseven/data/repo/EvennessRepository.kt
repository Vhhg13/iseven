package com.example.iseven.data.repo

import com.example.iseven.data.datasrc.local.KnownNumbersDB
import com.example.iseven.data.datasrc.remote.IsEven
import com.example.iseven.data.datasrc.remote.IsEvenAPI
import com.example.iseven.data.model.Evenness
import javax.inject.Inject

class EvennessRepository @Inject constructor(db: KnownNumbersDB, private val retrofit: IsEvenAPI) {
    private val dao = db.knownNumberDao()
    suspend fun isEven(n: Int): Evenness =
        dao.check(n) ?:
        retrofit.checkEven(n).toEvenness(n).also { dao.saveNumber(it) }
    suspend fun getNumbers() = dao.getAll()
    suspend fun remove(number: Int) = dao.remove(number)
}

private fun IsEven.toEvenness(n: Int) = Evenness(n, iseven, ad)
