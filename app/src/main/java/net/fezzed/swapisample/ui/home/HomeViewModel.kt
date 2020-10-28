package net.fezzed.swapisample.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.fezzed.swapisample.data.network.model.ResultModel
import net.fezzed.swapisample.domain.FetchHomeContentUseCase

class HomeViewModel @ViewModelInject constructor(
	@Assisted private val state: SavedStateHandle,
	private val fetchHomeContentUseCase: FetchHomeContentUseCase
) : ViewModel() {

	val text = MutableLiveData("No data")
	val result: MutableList<ResultModel> = mutableListOf()
	val loadingInProgress: MutableLiveData<Boolean> = MutableLiveData(false)

	init {
		fetchContent()
	}

	private fun fetchContent() {
		loadingInProgress.value = true
		viewModelScope.launch {
			try {
				val content = fetchHomeContentUseCase.fetchContentCoroutines()
				result.clear()
				result.addAll(content.results)
				text.value = content.results.toString()
			} catch (t: Throwable) {
				text.value = t.message
			} finally {
				loadingInProgress.value = false
			}
		}
	}

	companion object {
		val KEY = "resultKey"
	}
}