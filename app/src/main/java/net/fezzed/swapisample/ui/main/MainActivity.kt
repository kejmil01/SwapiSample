package net.fezzed.swapisample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import net.fezzed.swapisample.R
import net.fezzed.swapisample.data.repository.TestRepositoryWithState
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	@Inject lateinit var stateRepositoryWithState: TestRepositoryWithState

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}