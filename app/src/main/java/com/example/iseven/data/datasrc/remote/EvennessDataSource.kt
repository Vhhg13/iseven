package com.example.iseven.data.datasrc.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EvennessDataSource {
    suspend fun isEven(n: Int): IsEven {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.isevenapi.xyz/api/iseven/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IsEvenAPI::class.java)
        return service.checkEven(n)
    }
}