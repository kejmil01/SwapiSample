package net.fezzed.swapisample.data

import io.reactivex.rxjava3.core.Single
import net.fezzed.swapisample.domain.SwapiRepository

class SwapiRepositoryImpl : SwapiRepository {
	override fun fetchHomeContent(): Single<Any> {
		return Single.just("")
	}
}