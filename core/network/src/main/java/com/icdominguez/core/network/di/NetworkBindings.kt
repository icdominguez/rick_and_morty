package com.icdominguez.core.network.di

import com.icdominguez.core.network.RAMNetworkDataSource
import com.icdominguez.core.network.RetrofitRAMNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBindings {

    @Binds
    abstract fun bindNetworkDatasource(
        retrofitRAMNetwork: RetrofitRAMNetwork,
    ): RAMNetworkDataSource
}
