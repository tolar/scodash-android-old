package com.scodash.rest

import com.scodash.entity.ScoreDash
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ScodashBaseRouter {

	@GET("scoreDash")
	fun getScoreDashList(@Path("user_id") userId: Int): Single<Response<ScoreDash>>
}
