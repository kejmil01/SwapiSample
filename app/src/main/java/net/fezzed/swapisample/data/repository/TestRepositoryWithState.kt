package net.fezzed.swapisample.data.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class TestRepositoryWithState @Inject constructor() {

	var stateString: String = ""
		private set

	fun updateState(state: String) {
		stateString = state
	}
}