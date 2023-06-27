package com.example.summerpractiseapp.feature_favourite.di

import android.content.Context
import androidx.room.Room
import com.example.summerpractiseapp.feature_favourite.data.data_source.FavouriteContactsDao
import com.example.summerpractiseapp.feature_favourite.data.data_source.FavouriteDatabase
import com.example.summerpractiseapp.feature_favourite.data.data_source.FavouriteDatabase.Companion.FAVOURITE_DATABASE
import com.example.summerpractiseapp.feature_favourite.data.repository.FavouriteContactsRepositoryImpl
import com.example.summerpractiseapp.feature_favourite.domain.repository.FavouriteContactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteDI {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): FavouriteDatabase {
        return Room.databaseBuilder(
            context,
            FavouriteDatabase::class.java,
            FAVOURITE_DATABASE
        ).build()
    }

    @Singleton
    @Provides
    fun provideDaoFavourite(
        favouriteDb: FavouriteDatabase
    ): FavouriteContactsDao {
        return favouriteDb.getDao()
    }


    @Provides
    @Singleton
    fun provideRepositoryFavourite(
        dao: FavouriteContactsDao
    ): FavouriteContactsRepository {
        return FavouriteContactsRepositoryImpl(dao)
    }
}