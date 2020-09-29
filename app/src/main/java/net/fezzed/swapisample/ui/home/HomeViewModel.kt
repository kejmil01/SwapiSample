package net.fezzed.swapisample.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import net.fezzed.swapisample.domain.FetchHomeContentUseCase

class HomeViewModel @ViewModelInject constructor(
	fetchHomeContentUseCase: FetchHomeContentUseCase
) : ViewModel() {

	val text = MutableLiveData("No data")

	init {
		fetchHomeContentUseCase
			.fetchContent()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe { content, error ->
				text.value = content.results.toString()
			}
	}
}