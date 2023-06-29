package hr.sandro.chordiatocompose.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.sandro.chordiatocompose.data.local.TrackDatabase
import hr.sandro.chordiatocompose.data.local.repository.TrackLocalRepositoryImpl
import hr.sandro.chordiatocompose.data.remote.api.serp.SerpApi
import hr.sandro.chordiatocompose.data.remote.api.shazam.ShazamApi
import hr.sandro.chordiatocompose.data.remote.api.shazam.ShazamInterceptor
import hr.sandro.chordiatocompose.data.remote.repository.TrackRemoteRepositoryImpl
import hr.sandro.chordiatocompose.domain.repository.TrackLocalRepository
import hr.sandro.chordiatocompose.domain.repository.TrackRemoteRepository
import hr.sandro.chordiatocompose.domain.use_case.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChordiatoModule {
    @Provides
    @Singleton
    fun provideGetSheetUseCase(repository: TrackRemoteRepository): GetSheetUseCase {
        return GetSheetUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSongsUseCase(repository: TrackLocalRepository): GetSongsUseCase {
        return GetSongsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRecognizeSongUseCase(repository: TrackRemoteRepository): RecognizeSongUseCase {
        return RecognizeSongUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegisterRecognitionUseCase(repository: TrackLocalRepository): RegisterRecognitionUseCase {
        return RegisterRecognitionUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleFavouritesUseCase(repository: TrackLocalRepository): ToggleFavouriteUseCase {
        return ToggleFavouriteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideTrackLocalRepository(db: TrackDatabase): TrackLocalRepository {
        return TrackLocalRepositoryImpl(db.trackDao)
    }

    @Provides
    @Singleton
    fun provideTrackRemoteRepository(
        shazamApi: ShazamApi,
        serpApi: SerpApi,
    ): TrackRemoteRepository {
        return TrackRemoteRepositoryImpl(shazamApi, serpApi)
    }

    @Provides
    @Singleton
    fun provideTrackDatabase(app: Application): TrackDatabase {
        return Room.databaseBuilder(
            app, TrackDatabase::class.java, "word_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShazamApi(): ShazamApi {
        return Retrofit.Builder()
            .baseUrl(ShazamApi.SHAZAM_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(ShazamInterceptor())
                .build())
            .build()
            .create(ShazamApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSerpApi(): SerpApi {
        return Retrofit.Builder()
            .baseUrl(SerpApi.SERP_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SerpApi::class.java)
    }


}