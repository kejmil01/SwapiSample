package net.fezzed.swapisample.ui.test

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import net.fezzed.swapisample.data.repository.TestRepositoryWithState

class TestViewModel @ViewModelInject constructor(
	val stateRepository: TestRepositoryWithState
) : ViewModel() {
}