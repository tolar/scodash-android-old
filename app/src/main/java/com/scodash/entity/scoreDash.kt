package com.scodash.entity

data class ScoreDash(
		val name: String,
		val description: String,
		val itemList: List<ScoreDashItem>,
		val ownerName: String,
		val ownerEmail: String
)

data class ScoreDashItem(
		val name: String,
		val score: Int
)