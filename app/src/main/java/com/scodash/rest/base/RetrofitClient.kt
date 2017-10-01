package com.scodash.rest.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.scodash.ScodashConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

open class RetrofitClient() {

	val retrofitStandard: Retrofit by lazy {
		buildRetrofit(30)
	}
	val retrofitPatient: Retrofit by lazy {
		buildRetrofit(60)
	}


	private fun buildRetrofit(timeoutInSecond: Long): Retrofit {
		val builder = Retrofit.Builder()
		builder.baseUrl(ScodashConfig.REST_BASE_URL)
		builder.client(buildClient(timeoutInSecond))
		builder.addConverterFactory(createConverterFactory())
		builder.addCallAdapterFactory(createCallAdapterFactory())
		return builder.build()
	}

	private fun buildClient(timeoutInSecond: Long): OkHttpClient {
		val builder = OkHttpClient.Builder()
		builder.connectTimeout(timeoutInSecond, TimeUnit.SECONDS)
		builder.readTimeout(timeoutInSecond, TimeUnit.SECONDS)
		builder.writeTimeout(timeoutInSecond, TimeUnit.SECONDS)
		builder.addNetworkInterceptor(createLoggingInterceptor())
		return builder.build()
	}


	private fun createLoggingInterceptor(): Interceptor {
		val logger = HttpLoggingInterceptor.Logger { Timber.d(it) }
		val interceptor = HttpLoggingInterceptor(logger)
		interceptor.level = if (ScodashConfig.LOGS) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
		return interceptor
	}


	fun createGson(): Gson {
		val builder = GsonBuilder()
		builder.registerTypeAdapter(Date::class.java, DateTypeDeserializer())
		return builder.create()
	}

	private fun createConverterFactory(): Converter.Factory {
		return GsonConverterFactory.create(createGson())
	}


	private fun createCallAdapterFactory(): CallAdapter.Factory {
		return RxJava2CallAdapterFactory.create()
	}
}
