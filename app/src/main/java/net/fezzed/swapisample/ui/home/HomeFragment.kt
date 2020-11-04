package net.fezzed.swapisample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import net.fezzed.swapisample.databinding.HomeFragmentBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private lateinit var viewModel: HomeViewModel
	private lateinit var binding: HomeFragmentBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = HomeFragmentBinding.inflate(inflater, container, false).apply {
			lifecycleOwner = viewLifecycleOwner
		}

		binding.navigateButton.setOnClickListener {
			viewModel.stateRepository.updateState(viewModel.stateRepository.stateString + "a")
			findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTestFragment())
		}

		return binding.root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
		binding.viewModel = viewModel
	}

}