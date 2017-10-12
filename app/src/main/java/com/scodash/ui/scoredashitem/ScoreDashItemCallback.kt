package com.scodash.ui.scoredashitem

import com.scodash.entity.ScoreDashItem

interface ScoreDashItemCallback {
	fun onScoreDashItemClick(ScoreDashItem: ScoreDashItem)
}