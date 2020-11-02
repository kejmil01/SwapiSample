package net.fezzed.swapisample.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import net.fezzed.swapisample.data.network.model.ResultModel
import net.fezzed.swapisample.domain.FetchHomeContentUseCase

class HomeViewModel @ViewModelInject constructor(
	@Assisted private val state: SavedStateHandle,
	private val fetchHomeContentUseCase: FetchHomeContentUseCase
) : ViewModel() {

	private val queryObserver = buildQueryObserver()
	private val fetchingProcessObserver = buildFetchingProcessObserver()
	private val fetchingProcessState: MutableLiveData<FetchingProcessState> =
		state.getLiveData(KEY_FETCHING_PROCESS_STATE, FetchingProcessState.DEFAULT)
	private var fetchingJob: Job? = null

	val query: MutableLiveData<String> = state.getLiveData(KEY_QUERY, "")
	val text: MutableLiveData<String> = state.getLiveData(KEY_TEXT, "No data")
	val result: MutableLiveData<List<ResultModel>> = state.getLiveData(KEY_RESULT, emptyList())
	val loadingInProgress: MutableLiveData<Boolean> = state.getLiveData(KEY_PROGRESS, false)

	init {
		query.observeForever(queryObserver)
		fetchingProcessState.observeForever(fetchingProcessObserver)
	}

	private fun buildQueryObserver() = Observer<String> {
		tryToStartNewFetchingProcess(it)
	}

	private fun buildFetchingProcessObserver() = Observer<FetchingProcessState> {
		loadingInProgress.value = it.inProgress
		if(it.inProgress) {
			fetchContent(it.query)
		} else {
			/**
			 * Render
			 */
			result.value = it.result
			it.error?.let { error ->
				text.value =  error
			} ?: run {
				text.value = it.result.toString()
			}
		}
	}

	override fun onCleared() {
		query.removeObserver(queryObserver)
		fetchingProcessState.removeObserver(fetchingProcessObserver)
		super.onCleared()
	}

	private fun tryToStartNewFetchingProcess(queryString: String) {
		if(queryString != fetchingProcessState.value?.query ||
			true == fetchingProcessState.value?.result?.isNullOrEmpty()) {
			fetchingProcessState.value = FetchingProcessState(true, queryString, emptyList())
		}
	}

	private fun fetchContent(queryString: String) {
		fetchingJob?.cancel()
		fetchingJob = viewModelScope.launch {
			try {
				val content = fetchHomeContentUseCase.fetchContentCoroutines(queryString)
				if(isActive) {
					fetchingProcessState.value = FetchingProcessState(
						false,
						queryString,
						content.results,
						null
					)
				}
			} catch (t: Throwable) {
				if(isActive) {
					fetchingProcessState.value = FetchingProcessState(
						false,
						queryString,
						emptyList(),
						t.message
					)
				}
			}
		}
	}

	companion object {
		const val KEY_QUERY = "queryKey"
		const val KEY_RESULT = "resultKey"
		const val KEY_TEXT = "textKey"
		const val KEY_PROGRESS = "progressKey"
		const val KEY_FETCHING_PROCESS_STATE = "fetchingProcessKey"
	}

}