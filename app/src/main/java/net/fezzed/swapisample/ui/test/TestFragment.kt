package net.fezzed.swapisample.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import net.fezzed.swapisample.R

class TestFragment : Fragment() {

	companion object {
		fun newInstance() = TestFragment()
	}

	private lateinit var viewModel: TestViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.test_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val navigateButton: View = view.findViewById(R.id.navigateToHomeButton)
		navigateButton.setOnClickListener {
			findNavController().navigate(TestFragmentDirections.actionTestFragmentToHomeFragment())
		}
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
		// TODO: Use the ViewModel
	}

}