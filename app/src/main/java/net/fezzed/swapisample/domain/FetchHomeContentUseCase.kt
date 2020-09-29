package net.fezzed.swapisample.domain

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FetchHomeContentUseCase @Inject constructor(private val repository: SwapiRepository) {
	fun fetchContent(): Single<Any> {
		return repository.fetchHomeContent()
	}
}