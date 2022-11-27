package com.smartshopper.smart_shopper.database

import androidx.room.Database

@Database(entities = [ProductEntities::class, DealsEntities::class], version = 1)
abstract class AppDatabase {
    abstract fun Dao(): Dao
}