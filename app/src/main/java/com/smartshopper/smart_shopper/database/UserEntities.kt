package com.smartshopper.smart_shopper.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserEntities(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "state") var state: String? = null,
    @ColumnInfo(name = "code") var code: String? = null,
) : Parcelable