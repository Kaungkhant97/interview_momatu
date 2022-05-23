package com.interview.momatu.di

import android.content.Context
import com.interview.momatu.model.DefaultPhotoRepository
import com.interview.momatu.model.PhotoRepository
import com.interview.momatu.network.DefaultRemoteDataSource
import com.interview.momatu.network.PhotoService
import com.interview.momatu.network.RemoteDataSource

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providePhotoRemoteDataSource(photoService: PhotoService): RemoteDataSource {
        return DefaultRemoteDataSource(photoService);
    }


}


@Module
@InstallIn(SingletonComponent::class)
object PhotoRepositoryModule {

    @Singleton
    @Provides
    fun providePhotoRepository(
        remotePhotoDataSource: RemoteDataSource,
    ): PhotoRepository {
        return DefaultPhotoRepository(
            remotePhotoDataSource
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePhotoService(retrofit: Retrofit): PhotoService {
        return retrofit.create(PhotoService::class.java);
    }


    @Singleton
    @Provides
    fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return client;
    }

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging;
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl("https://picsum.photos/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit;
    }
}
