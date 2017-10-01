package com.scodash.rest.base

import retrofit2.Retrofit
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

class RetrofitMockClient() {
	private fun mockRetrofit(): Retrofit {
		return RetrofitClient().retrofitStandard
	}

	fun <T> getMockDelegate(service: Class<T>): BehaviorDelegate<T> {
		val behavior = NetworkBehavior.create()
		/**
		 * "By default, instances of this class will use a 2 second delay with 40% variance and failures will occur 3%
		 * of the time."
		 * http://square.github.io/retrofit/2.x/retrofit-mock/retrofit2/mock/NetworkBehavior.html
		 */
		behavior.setErrorPercent(0)
		behavior.setFailurePercent(0)
		behavior.setVariancePercent(0)
		val mockRetrofit = MockRetrofit.Builder(mockRetrofit())
				.networkBehavior(behavior)
				.build()

		return mockRetrofit.create(service)
	}
}