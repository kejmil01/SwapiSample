package net.fezzed.swapisample.domain

import io.reactivex.rxjava3.core.Single

class FetchHomeContentUseCase(private val repository: SwapiRepository) {
	fun fetchContent(): Single<Any> {
		return repository.fetchHomeContent()
	}
}