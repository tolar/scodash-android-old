package com.scodash

import android.content.Context

object ContextProvider {
	lateinit var applictionContext: Context

	fun initialize(context: Context) {
		this.applictionContext = context
	}
}