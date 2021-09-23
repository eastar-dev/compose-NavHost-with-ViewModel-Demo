package dev.eastar.composewithviewmodeldemo.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.eastar.composewithviewmodeldemo.data.repository.HelloRepositoryImpl
import dev.eastar.composewithviewmodeldemo.domain.repository.HelloRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HelloRepositoryModule {

    @Singleton
    @Provides
    fun provideHelloRepository(): HelloRepository {
        return HelloRepositoryImpl()
    }
}