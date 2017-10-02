package com.scodash.rest

import com.scodash.entity.ScoreDash
import com.scodash.rest.base.RetrofitClient
import com.scodash.rest.base.RetrofitMockClient
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls
import java.io.IOException

open class ScodashRouter : ScodashBaseRouter {

	private var mockDelegate: BehaviorDelegate<ScodashBaseRouter> = RetrofitMockClient().getMockDelegate(ScodashBaseRouter::class.java)

	override fun getScoreDashList(userId: Int): Single<Response<ScoreDash>> {

		var response: Call<ScoreDash> = Calls.failure(IOException("Failed"))

		javaClass.classLoader.getResourceAsStream("scodash_data_mock.json").bufferedReader().use {
			response = Calls.response(RetrofitClient().createGson().fromJson<ScoreDash>(it.readText(), ScoreDash::class.java))
		}

		return mockDelegate.returning(response).getScoreDashList(userId)
	}

}