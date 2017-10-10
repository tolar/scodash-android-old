package com.scodash.rest.base

import com.scodash.entity.ScoreDash

class ApiResponse {
	var values: List<ScoreDash>? = null
		private set
	var error: Throwable? = null
		private set

	constructor(issues: List<ScoreDash>?) {
		this.values = issues
		this.error = null
	}

	constructor(error: Throwable) {
		this.error = error
		this.values = null
	}
}