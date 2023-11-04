package com.example.iseven

import android.app.Application
import androidx.room.Room
import com.example.iseven.data.datasrc.remote.EvennessDataSource
import com.example.iseven.data.datasrc.local.KnownNumbersDB
import com.example.iseven.data.datasrc.local.KnownNumbersDataSource
import com.example.iseven.data.repo.EvennessRepository
import com.example.iseven.data.repo.KnownNumbersRepository

class App : Application() {
    val evennessDataSource = EvennessDataSource()
    val evennessRepository = EvennessRepository(evennessDataSource)

    lateinit var db: KnownNumbersDB
    lateinit var knownNumbersDataSource: KnownNumbersDataSource
    lateinit var knownNumbersRepository: KnownNumbersRepository
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, KnownNumbersDB::class.java, "known-numbers").build()
        knownNumbersDataSource = KnownNumbersDataSource(db)
        knownNumbersRepository = KnownNumbersRepository(knownNumbersDataSource)
    }

}
