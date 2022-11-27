package com.smartshopper.smart_shopper.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "products")
data class ProductEntities(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "storeName") var storeName: String? = null,
    @ColumnInfo(name = "productName") var productName: String? = null,
    @ColumnInfo(name = "price") var price: String? = null,
) : Parcelable
