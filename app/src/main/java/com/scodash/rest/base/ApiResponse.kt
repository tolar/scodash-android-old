package com.scodash.rest.base

import com.scodash.entity.ScoreDash

class ApiResponse {
	var value: ScoreDash? = null
		private set
	var error: Throwable? = null
		private set

	constructor(issues: ScoreDash?) {
		this.value = issues
		this.error = null
	}

	constructor(error: Throwable) {
		this.error = error
		this.value = null
	}
}