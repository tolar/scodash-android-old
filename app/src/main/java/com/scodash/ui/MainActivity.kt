package com.scodash.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.scodash.R
import com.scodash.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

	private lateinit var viewModel: MainViewModel
	private lateinit var viewDataBinding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

		viewDataBinding = ActivityMainBinding.inflate(layoutInflater)
		viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		viewDataBinding.viewModel = viewModel
		viewDataBinding.executePendingBindings()

	}


	override fun onSomeUserAction() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}
