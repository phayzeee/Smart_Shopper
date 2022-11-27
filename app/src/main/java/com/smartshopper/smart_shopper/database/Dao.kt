package com.smartshopper.smart_shopper.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Query("SELECT * FROM products ORDER BY uid")
    fun getProducts(): ArrayList<ProductEntities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productEntities: ProductEntities)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeal(dealsEntities: DealsEntities)

    @Query("SELECT * FROM deals ORDER BY uid")
    fun getDeals(): ArrayList<DealsEntities>
}