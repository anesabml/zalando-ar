package com.anesabml.zalando.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anesabml.zalando.utils.Differentiable
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo val images: List<String>,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val category: String,
    @ColumnInfo val price: Float,
    @ColumnInfo val availableSizes: List<String>,
    @ColumnInfo val availableColors: List<String>,
    @ColumnInfo val isBookmarked: Boolean,
) : Differentiable {
    override val diffId: Int
        get() = id
}