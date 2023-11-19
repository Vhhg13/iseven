package com.example.iseven.di

import android.content.Context
import androidx.room.Room
import com.example.iseven.data.datasrc.local.KnownNumbersDB
import com.example.iseven.data.datasrc.remote.IsEvenAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {
    @Provides
    @Singleton
    fun provideKnownNumbersDB(@ApplicationContext context: Context): KnownNumbersDB{
        return Room.databaseBuilder(context, KnownNumbersDB::class.java, "known-numbers").build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): IsEvenAPI{
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.isevenapi.xyz/api/iseven/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(IsEvenAPI::class.java)
    }
}