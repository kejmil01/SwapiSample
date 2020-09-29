package net.fezzed.swapisample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.fezzed.swapisample.data.SwapiRepositoryImpl
import net.fezzed.swapisample.domain.SwapiRepository

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {
	@Provides
	fun provideSwapiRepository(repository: SwapiRepositoryImpl): SwapiRepository = repository
}