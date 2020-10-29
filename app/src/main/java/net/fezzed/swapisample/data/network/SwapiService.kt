package net.fezzed.swapisample.data.network

import io.reactivex.rxjava3.core.Single
import net.fezzed.swapisample.data.network.model.SearchResultModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SwapiService {
	@GET("people/")
	fun getUsers(@Query("search") query: String? = null): Single<SearchResultModel>

	@GET("people/")
	suspend fun getUsersCoroutines(@Query("search") query: String? = null): SearchResultModel
}