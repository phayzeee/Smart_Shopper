package com.smartshopper.smart_shopper.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "grocery")
data class GroceryEntities(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "storeName") var storeName: String? = null,
    @ColumnInfo(name = "productName") var productName: String? = null,
    @ColumnInfo(name = "price") var price: String? = null,
) : Parcelable
