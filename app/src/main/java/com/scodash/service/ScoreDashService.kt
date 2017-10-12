package com.scodash.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.scodash.entity.ScoreDash
import com.scodash.rest.ScodashBaseRouter
import com.scodash.rest.ScodashRouter
import com.scodash.rest.base.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class ScoreDashService {
	private val mApiService: ScodashBaseRouter

	init {
		mApiService = ScodashRouter()
	}

	fun getScoreDashList(userId: Int): LiveData<ApiResponse> {
		val liveData = MutableLiveData<ApiResponse>()

		val call: Call<ScoreDash> = mApiService.getScoreDashLive(userId)
		call.enqueue(object : Callback<ScoreDash> {
			override fun onResponse(call: Call<ScoreDash>, response: Response<ScoreDash>) {
				liveData.value = ApiResponse(response.body())
			}

			override fun onFailure(call: Call<ScoreDash>, t: Throwable) {
				Timber.e(t)
				liveData.value = ApiResponse(t)
			}
		})
		return liveData
	}

}