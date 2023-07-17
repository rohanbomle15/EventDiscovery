package com.robo.eventdiscovery.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.robo.eventdiscovery.model.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDB : RoomDatabase() {

    abstract fun getProductDAO() : ProductDAO

}