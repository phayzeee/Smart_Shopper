package com.smartshopper.smart_shopper.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "deals")
data class DealsEntities(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "storeName") var storeName: String? = null,
    @ColumnInfo(name = "product") var product: String? = null,
    @ColumnInfo(name = "deal") var deal: String? = null,
    @ColumnInfo(name = "duration") var duration: String? = null
) : Parcelable
