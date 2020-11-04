package net.fezzed.swapisample.ui.secondactivity

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import net.fezzed.swapisample.R
import net.fezzed.swapisample.data.repository.TestRepositoryWithState
import javax.inject.Inject

//@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

	//@Inject lateinit var stateRepositoryWithState: TestRepositoryWithState

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_second)
		setSupportActionBar(findViewById(R.id.toolbar))

		findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show()
		}
	}

	override fun onStart() {
		super.onStart()
	}
}