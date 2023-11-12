package com.example.iseven.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.iseven.data.datasrc.local.KnownNumbersDB
import com.example.iseven.data.datasrc.local.KnownNumbersDataSource
import com.example.iseven.data.datasrc.remote.EvennessDataSource
import com.example.iseven.data.datasrc.remote.IsEvenAPI
import com.example.iseven.data.repo.EvennessRepository
import com.example.iseven.data.repo.ImageRepository
import com.example.iseven.data.repo.KnownNumbersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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
    fun provideEvennessDataSource(rf: IsEvenAPI): EvennessDataSource{
        return EvennessDataSource(rf)
    }
    @Provides
    @Singleton
    fun provideEvennessRepository(ds: EvennessDataSource): EvennessRepository{
        return EvennessRepository(ds)
    }

    @Provides
    @Singleton
    fun provideKnownNumbersDB(@ApplicationContext context: Context): KnownNumbersDB{
        return Room.databaseBuilder(context, KnownNumbersDB::class.java, "known-numbers").build()
    }

    @Provides
    @Singleton
    fun provideKnownNumbersDataSource(db: KnownNumbersDB): KnownNumbersDataSource{
        return KnownNumbersDataSource(db)
    }

    @Provides
    @Singleton
    fun provideKnownNumbersRepo(ds: KnownNumbersDataSource): KnownNumbersRepository{
        return KnownNumbersRepository(ds)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): IsEvenAPI{
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.isevenapi.xyz/api/iseven/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(IsEvenAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepo(app: Application) = ImageRepository(app)
}