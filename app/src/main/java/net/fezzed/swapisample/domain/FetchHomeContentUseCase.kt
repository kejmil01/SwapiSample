package net.fezzed.swapisample.domain

import io.reactivex.rxjava3.core.Single
import net.fezzed.swapisample.data.network.model.SearchResultModel
import javax.inject.Inject

class FetchHomeContentUseCase @Inject constructor(private val repository: SwapiRepository) {

	fun fetchContent(): Single<SearchResultModel> {
		return repository.fetchHomeContent()
	}
}