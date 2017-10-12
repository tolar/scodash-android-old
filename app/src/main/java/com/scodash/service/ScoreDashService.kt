package com.scodash.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.scodash.ScodashConfig
import com.scodash.entity.ScoreDash
import com.scodash.rest.ScodashBaseRouter
import com.scodash.rest.base.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ScoreDashService {
	private val mApiService: ScodashBaseRouter

	init {
		val retrofit = Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(ScodashConfig.REST_BASE_URL)
				.build()
		mApiService = retrofit.create<ScodashBaseRouter>(ScodashBaseRouter::class.java)
	}

	fun getScoreDashList(userId: Int): LiveData<ApiResponse> {
		val liveData = MutableLiveData<ApiResponse>()

		val call: Call<List<ScoreDash>> = mApiService.getScoreDashListLive(userId)
		call.enqueue(object : Callback<List<ScoreDash>> {
			override fun onResponse(call: Call<List<ScoreDash>>, response: Response<List<ScoreDash>>) {
				liveData.value = ApiResponse(response.body())
			}

			override fun onFailure(call: Call<List<ScoreDash>>, t: Throwable) {
				liveData.value = ApiResponse(t)
			}
		})
		return liveData
	}

}