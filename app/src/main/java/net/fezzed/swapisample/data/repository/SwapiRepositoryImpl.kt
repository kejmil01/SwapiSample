package net.fezzed.swapisample.data.repository

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import net.fezzed.swapisample.data.network.SwapiService
import net.fezzed.swapisample.data.network.model.SearchResultModel
import net.fezzed.swapisample.domain.SwapiRepository
import javax.inject.Inject

class SwapiRepositoryImpl @Inject constructor(
	private val swapiService: SwapiService
) : SwapiRepository {
	override fun fetchHomeContent(): Single<SearchResultModel> {
		return swapiService.getUsers().subscribeOn(Schedulers.io())
	}
}