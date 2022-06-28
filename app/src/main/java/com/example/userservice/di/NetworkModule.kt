package com.example.userservice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun client(): HttpClient {
        return HttpClient(Android) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(
                        kotlinx.serialization.json.Json  {
                            ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                        }
                    )
                }
                install(Logging){
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }

    }
}