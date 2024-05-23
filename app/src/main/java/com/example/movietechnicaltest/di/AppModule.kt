package com.example.movietechnicaltest.di

import android.app.Application
import androidx.room.Room
import com.example.movietechnicaltest.core.Constants
import com.example.movietechnicaltest.data.local.YuGiOhDatabase
import com.example.movietechnicaltest.data.remote.YuGiOhApi
import com.example.movietechnicaltest.data.repository.CardsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWebService(): YuGiOhApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YuGiOhApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        rest: YuGiOhApi,
        db: YuGiOhDatabase,
        app: Application
    ): CardsRepoImpl {
        return CardsRepoImpl(rest, db, app)
    }


    @Provides
    @Singleton
    fun provideYuGiOhDatabase(app: Application): YuGiOhDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = YuGiOhDatabase::class.java,
            name = "yugiohdb.db"
        ).build()
    }
}