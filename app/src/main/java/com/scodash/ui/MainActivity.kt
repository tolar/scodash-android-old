package com.scodash.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.scodash.R

class MainActivity : AppCompatActivity(), MainView {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}


	override fun onSomeUserAction() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}
