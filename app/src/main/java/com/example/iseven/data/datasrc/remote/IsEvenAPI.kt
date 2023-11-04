package com.example.iseven.data.datasrc.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface IsEvenAPI {
    @GET("{id}")
    suspend fun checkEven(@Path("id") n: Int): IsEven
}