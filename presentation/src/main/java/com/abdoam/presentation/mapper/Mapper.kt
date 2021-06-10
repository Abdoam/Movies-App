package com.abdoam.presentation.mapper

interface Mapper<Presentation, Domain> {

    fun toDomain(presentation: Presentation): Domain

    fun toPresentation(domain: Domain): Presentation

}