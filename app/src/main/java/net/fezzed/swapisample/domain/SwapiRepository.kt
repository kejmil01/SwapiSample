package net.fezzed.swapisample.domain

import io.reactivex.rxjava3.core.Single

interface SwapiRepository {
	fun fetchHomeContent(): Single<Any>
}