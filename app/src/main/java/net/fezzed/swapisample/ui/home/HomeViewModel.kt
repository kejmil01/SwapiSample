package net.fezzed.swapisample.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import net.fezzed.swapisample.domain.FetchHomeContentUseCase

class HomeViewModel @ViewModelInject constructor(
	private val fetchHomeContentUseCase: FetchHomeContentUseCase
) : ViewModel() {
	// TODO: Implement the ViewModel
}