package com.anesabml.zalando.domain.mapper

interface ModuleEntityMapper<Module, Entity> {

    fun toEntity(module: Module): Entity

    fun fromEntity(entity: Entity): Module
}