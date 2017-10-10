package com.scodash

import android.app.Application
import android.content.Context
import android.os.StrictMode
import timber.log.Timber


class ScodashApplication : Application() {

	lateinit var context: Context

	override fun onCreate() {
		super.onCreate()

		ContextProvider.initialize(context)

		if (BuildConfig.DEBUG) {

			StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
					//					.detectDiskReads()
					//					.detectDiskWrites()
					//					.detectNetwork()
					.detectAll()// for all detectable problems
					.penaltyLog()
					.build())
			StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
					//					.detectLeakedSqlLiteObjects()
					//					.detectLeakedClosableObjects()
					.detectAll()
					.penaltyLog()
					//.penaltyDeath()
					.build())
		}

		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}
	}
}