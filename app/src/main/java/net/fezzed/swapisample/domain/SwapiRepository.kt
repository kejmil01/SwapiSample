package net.fezzed.swapisample.domain

import io.reactivex.rxjava3.core.Single
import net.fezzed.swapisample.data.network.model.SearchResultModel

interface SwapiRepository {
	fun fetchHomeContent(): Single<SearchResultModel>

	suspend fun fetchHomeContentCoroutines(): SearchResultModel
}