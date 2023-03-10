package com.smartshopper.smart_shopper.database

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Query("SELECT * FROM products ORDER BY uid")
    fun getProducts(): List<ProductEntities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productEntities: ProductEntities)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGrocery(productEntities: GroceryEntities)

    @Query("SELECT * FROM grocery ORDER BY uid")
    fun getGrocery(): List<GroceryEntities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeal(dealsEntities: DealsEntities)

    @Query("SELECT * FROM deals ORDER BY uid")
    fun getDeals(): List<DealsEntities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun adduser(userEntities: UserEntities)

    @Query("SELECT * FROM user ORDER BY uid")
    fun getUser(): UserEntities

    @Delete
    fun deleteGrocery(groceryEntities: GroceryEntities)
}