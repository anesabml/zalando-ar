package com.anesabml.zalandoar.data.mapper

import com.anesabml.zalandoar.data.entity.ProductEntity
import com.anesabml.zalandoar.domain.mapper.ModuleEntityMapper
import com.anesabml.zalandoar.domain.model.Product
import java.util.Date

object DatabaseMapper : ModuleEntityMapper<Product, ProductEntity> {
    override fun toEntity(module: Product): ProductEntity =
        ProductEntity(
            module.id,
            module.images,
            module.name,
            module.description,
            module.category,
            module.addedAt.time,
            module.price,
            module.availableSizes,
            module.availableColors,
            module.isBookmarked,
        )

    override fun fromEntity(entity: ProductEntity): Product =
        Product(
            entity.id,
            entity.images,
            entity.name,
            entity.description,
            entity.category,
            Date(entity.addedAt),
            entity.price,
            entity.availableSizes,
            entity.availableColors,
            entity.isBookmarked,
        )
}