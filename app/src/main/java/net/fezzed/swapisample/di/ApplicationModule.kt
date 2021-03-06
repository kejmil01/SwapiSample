package net.fezzed.swapisample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.fezzed.swapisample.data.network.SwapiServiceBuilder
import net.fezzed.swapisample.data.repository.SwapiRepositoryImpl
import net.fezzed.swapisample.domain.SwapiRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

	@Provides
	@Singleton
	fun provideApiService() = SwapiServiceBuilder.build()

	@Provides
	fun provideSwapiRepository(repository: SwapiRepositoryImpl): SwapiRepository = repository
}