package com.example.iseven.data.datasrc.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class EvennessDataSource @Inject constructor(private val retrofit: IsEvenAPI) {
    suspend fun isEven(n: Int) = retrofit.checkEven(n)
}