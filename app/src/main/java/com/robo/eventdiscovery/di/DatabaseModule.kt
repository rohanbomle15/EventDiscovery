package com.robo.eventdiscovery.di

import android.content.Context
import androidx.room.Room
import com.robo.eventdiscovery.db.ProductDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    fun provideProductDB(context: Context): ProductDB {
        return Room.databaseBuilder(context, ProductDB::class.java, "ProductDB").build()
    }

    @Provides
    @Singleton
    fun provideDao(db: ProductDB) = db.getProductDAO()
}