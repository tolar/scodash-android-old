package com.scodash.rest

import com.scodash.entity.ScoreDash
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ScodashBaseRouter {

	@GET("scoreDash/{user_id}")
	fun getScoreDashList(@Path("user_id") userId: Int): Single<Response<ScoreDash>>

	@GET("scoreDash/{user_id}")
	fun getScoreDashLive(@Path("user_id") userId: Int): Call<ScoreDash>
}
