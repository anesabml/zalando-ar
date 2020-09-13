package com.anesabml.zalandoar.data

import com.anesabml.zalandoar.data.entity.ProductEntity
import com.anesabml.zalandoar.domain.model.ProductCategory

object DummyData {

    val products = listOf(
        ProductEntity(
            id = 1,
            images = listOf(
                "https://images.unsplash.com/photo-1499971442178-8c10fdf5f6ac?ixlib=rb-1.2.1&auto=format&fit=crop&w=674&q=80",
                "https://images.unsplash.com/photo-1499971856191-1a420a42b498?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=658&q=80"
            ),
            name = "Shirt",
            description = "Lorem impsum, bla bla",
            addedAt = 1598771511773L,
            category = ProductCategory.MEN,
            price = 25.0F,
            availableSizes = listOf(
                "s",
                "m",
                "l"
            ),
            availableColors = listOf(
                "#FFFFFF",
                "#E0E0E0",
                "#0000FF"
            ),
            isBookmarked = false
        ),
        ProductEntity(
            id = 2,
            images = listOf(
                "https://images.unsplash.com/photo-1499971442178-8c10fdf5f6ac?ixlib=rb-1.2.1&auto=format&fit=crop&w=674&q=80",
                "https://images.unsplash.com/photo-1499971856191-1a420a42b498?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=658&q=80"
            ),
            name = "Shirt",
            description = "Lorem impsum, bla bla",
            addedAt = 1598771511773L,
            category = ProductCategory.MEN,
            price = 25.0F,
            availableSizes = listOf(
                "s",
                "m",
                "l"
            ),
            availableColors = listOf(
                "#FFFFFF",
                "#E0E0E0",
                "#0000FF"
            ),
            isBookmarked = false
        )
    )
}