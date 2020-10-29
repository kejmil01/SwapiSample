package net.fezzed.swapisample.data.repository

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import net.fezzed.swapisample.data.network.SwapiService
import net.fezzed.swapisample.data.network.model.SearchResultModel
import net.fezzed.swapisample.domain.SwapiRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SwapiRepositoryImpl @Inject constructor(
	private val swapiService: SwapiService
) : SwapiRepository {
	override fun fetchHomeContent(queryString: String): Single<SearchResultModel> {
		return swapiService.getUsers(queryString)
			.subscribeOn(Schedulers.io())
			.delay(3, TimeUnit.SECONDS)
	}

	//TODO add a defaultDispatcher constructor property to SwapiRepositoryImpl and use it here instead of Dispatchers.Default
	override suspend fun fetchHomeContentCoroutines(queryString: String): SearchResultModel = withContext(Dispatchers.Default) {
		/**
		 * Wait at least 3000 millis before returning a result
		 */

		val fetchingResult = async {
			swapiService.getUsersCoroutines(queryString)
		}
		delay(3000)
		return@withContext fetchingResult.await()
	}
}