package com.example.iseven.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.iseven.data.datasrc.local.KnownNumbersDataSource
import com.example.iseven.data.model.Evenness
import com.example.iseven.data.model.KnownListItem
import com.example.iseven.data.model.KnownNumberEntity

class KnownNumbersRepository(val dataSource: KnownNumbersDataSource) {

    suspend fun getNumbers(): List<KnownListItem> = dataSource.getNumbers().map { KnownListItem(it.number, it.parity) }

    suspend fun submit(evenness: Evenness){
        dataSource.submit(KnownNumberEntity(evenness.number, evenness.isEven, evenness.ad))
    }

    suspend fun check(n: Int): Evenness{
        val entity: KnownNumberEntity = dataSource.check(n)
        return Evenness(entity.number, entity.parity, entity.ad)
    }

    suspend fun remove(n: Int){
        dataSource.remove(n)
    }
}