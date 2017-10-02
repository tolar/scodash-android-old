package com.scodash.entity

import com.google.gson.annotations.SerializedName

data class ScoreDash(
		@SerializedName("name") val name: String,
		@SerializedName("description") val description: String,
		@SerializedName("items") val itemList: List<ScoreDashItem>,
		@SerializedName("ownerName") val ownerName: String,
		@SerializedName("ownerEmail") val ownerEmail: String
)

data class ScoreDashItem(
		@SerializedName("name") val name: String,
		@SerializedName("score") val score: Int
)