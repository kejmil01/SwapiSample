package net.fezzed.swapisample.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import net.fezzed.swapisample.data.network.model.ResultModel
import net.fezzed.swapisample.domain.FetchHomeContentUseCase

class HomeViewModel @ViewModelInject constructor(
	@Assisted private val state: SavedStateHandle,
	fetchHomeContentUseCase: FetchHomeContentUseCase
) : ViewModel() {

	val text = MutableLiveData("No data")
	val result: MutableList<ResultModel> = mutableListOf()
	val loadingInProgress: MutableLiveData<Boolean> = MutableLiveData(false)

	init {
		loadingInProgress.value = true
		fetchHomeContentUseCase
			.fetchContent()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe { content, error ->
				content?.let {
					result.clear()
					result.addAll(content.results)
					text.value = content.results.toString()
				} ?: run {
					text.value = error.message
				}
				loadingInProgress.value = false
			}
	}

	companion object {
		val KEY = "resultKey"
	}
}