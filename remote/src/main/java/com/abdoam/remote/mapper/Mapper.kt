package com.abdoam.remote.mapper

interface Mapper<Network, Data> {

    fun toData(network: Network): Data

    fun toNetwork(data: Data): Network

}