package com.robo.eventdiscovery.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robo.eventdiscovery.model.Product


@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(products : List<Product>)

    @Query("SELECT * FROM Product")
    suspend fun getProducts() : List<Product>

}