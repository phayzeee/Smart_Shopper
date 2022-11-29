package com.smartshopper.smart_shopper.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntities::class, DealsEntities::class, UserEntities::class, GroceryEntities::class],
    version = 8
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun Dao(): Dao
}