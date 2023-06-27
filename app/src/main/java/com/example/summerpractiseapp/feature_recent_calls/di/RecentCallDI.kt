package com.example.summerpractiseapp.feature_recent_calls.di

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import com.example.summerpractiseapp.feature_recent_calls.data.data_source.RecentCallsDao
import com.example.summerpractiseapp.feature_recent_calls.data.data_source.RecentCallsDatabase
import com.example.summerpractiseapp.feature_recent_calls.data.data_source.RecentCallsDatabase.Companion.RECENT_CALLS_DATABASE
import com.example.summerpractiseapp.feature_recent_calls.data.repository.RecentCallsRepositoryImpl
import com.example.summerpractiseapp.feature_recent_calls.domain.repository.RecentCallsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecentCallDI {
    @Provides
    @Singleton
    fun provideRecentCallsDatabase(@ApplicationContext context: Context): RecentCallsDatabase {
        return Room.databaseBuilder(
            context,
            RecentCallsDatabase::class.java,
            RECENT_CALLS_DATABASE
        ).build()
    }

    @Singleton
    @Provides
    fun provideRecentCallsDao(db: RecentCallsDatabase): RecentCallsDao {
        return db.getDao()
    }

    @Singleton
    @Provides
    fun provideRecentCallsRepository(dao: RecentCallsDao) : RecentCallsRepository{
        return RecentCallsRepositoryImpl(dao)
    }
}