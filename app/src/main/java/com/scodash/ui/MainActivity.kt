package com.scodash.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.scodash.R
import com.scodash.databinding.ActivityMainBinding
import com.scodash.entity.ScoreDashItem
import com.scodash.ui.scoredashitem.ScoreDashItemAdapter
import com.scodash.ui.scoredashitem.ScoreDashItemCallback

class MainActivity : AppCompatActivity(), MainView, ScoreDashItemCallback {

	private lateinit var viewModel: MainViewModel
	private lateinit var viewDataBinding: ActivityMainBinding
	private lateinit var scoreDashItemAdapter: ScoreDashItemAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
		scoreDashItemAdapter = ScoreDashItemAdapter(this)

		subscribeUi(viewModel, scoreDashItemAdapter)

		viewDataBinding = ActivityMainBinding.inflate(layoutInflater)
		viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		viewDataBinding.viewModel = viewModel
		viewDataBinding.scoreDashList.setAdapter(scoreDashItemAdapter)
		viewDataBinding.executePendingBindings()


	}

	private fun subscribeUi(mainViewModel: MainViewModel, scoreDashItemAdapter: ScoreDashItemAdapter) {
		mainViewModel.loadIssues(1).observe(this, Observer { apiResponse ->

			apiResponse?.value?.let { scoreDash ->
				scoreDashItemAdapter.setProductList(scoreDash.itemList)
			}
			apiResponse?.error?.let {
				Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
			}
		})
	}

	override fun onSomeUserAction() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun onScoreDashItemClick(ScoreDashItem: ScoreDashItem) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}
