package com.example.iseven.data.repo

import com.example.iseven.data.datasrc.remote.EvennessDataSource
import com.example.iseven.data.model.Evenness

class EvennessRepository(private val evennessDataSource: EvennessDataSource) {
    suspend fun isEven(n: Int): Evenness {
        val res = evennessDataSource.isEven(n)
        return Evenness(n, res.iseven, res.ad)
    }
}