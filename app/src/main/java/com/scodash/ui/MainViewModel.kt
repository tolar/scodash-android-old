package com.scodash.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.scodash.rest.base.ApiResponse
import com.scodash.service.ScoreDashService

class MainViewModel : ViewModel() {

	private var apiResponse: MediatorLiveData<ApiResponse> = MediatorLiveData()
	private var scoreDashService: ScoreDashService = ScoreDashService()

	fun loadIssues(userId: Int): LiveData<ApiResponse> {
		apiResponse.addSource(scoreDashService.getScoreDashList(userId)) { t ->
			apiResponse.value = t
		}
		return apiResponse
	}

}