package net.fezzed.swapisample.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.fezzed.swapisample.data.network.model.ResultModel
import net.fezzed.swapisample.domain.FetchHomeContentUseCase

class HomeViewModel @ViewModelInject constructor(
	@Assisted private val state: SavedStateHandle,
	private val fetchHomeContentUseCase: FetchHomeContentUseCase
) : ViewModel() {

	private val queryObserver = Observer<String> { fetchContent(it) }

	val query: MutableLiveData<String> = state.getLiveData(KEY_QUERY, "")
	val text: MutableLiveData<String> = state.getLiveData(KEY_TEXT, "No data")
	val result: MutableLiveData<List<ResultModel>> = state.getLiveData(KEY_RESULT, emptyList())
	val loadingInProgress: MutableLiveData<Boolean> = state.getLiveData(KEY_PROGRESS, false)

	//TODO test advanced proccess restoration - look for SwapiSearchSample(MVI) for examples
	init {
		query.observeForever(queryObserver)
	}

	override fun onCleared() {
		query.removeObserver(queryObserver)
		super.onCleared()
	}

	private fun fetchContent(queryString: String) {
		state.set(KEY_PROGRESS, true)
		viewModelScope.launch {
			try {
				val content = fetchHomeContentUseCase.fetchContentCoroutines(queryString)
				state.set(KEY_RESULT, content.results)
				state.set(KEY_TEXT, content.results.toString())
			} catch (t: Throwable) {
				text.value = t.message
			} finally {
				state.set(KEY_PROGRESS, false)
			}
		}
	}

	companion object {
		const val KEY_QUERY = "queryKey"
		const val KEY_RESULT = "resultKey"
		const val KEY_TEXT = "textKey"
		const val KEY_PROGRESS = "progressKey"
	}
}