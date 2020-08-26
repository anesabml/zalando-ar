package com.anesabml.zalando.data.mapper

import com.anesabml.zalando.data.entity.ProductEntity
import com.anesabml.zalando.domain.mapper.ModuleEntityMapper
import com.anesabml.zalando.domain.model.Product

object DatabaseMapper : ModuleEntityMapper<Product, ProductEntity> {
    override fun toEntity(module: Product): ProductEntity =
        ProductEntity(
            module.id,
            module.images,
            module.name,
            module.description,
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
            entity.price,
            entity.availableSizes,
            entity.availableColors,
            entity.isBookmarked,
        )
}