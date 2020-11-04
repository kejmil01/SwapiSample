package net.fezzed.swapisample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import net.fezzed.swapisample.data.repository.TestRepositoryWithState

/*
@Module
@InstallIn(ActivityComponent::class)
abstract class TestStateModule {

	@Binds
	abstract fun bindTestRepository(
		repository: TestRepositoryWithState
	): TestRepositoryWithState
}*/
