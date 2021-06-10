package com.abdoam.data.mapper

interface Mapper<Data, Domain> {

    fun toData(domain: Domain): Data

    fun toDomain(data: Data): Domain

}