package com.scodash.ui.utility

import android.databinding.BindingAdapter
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.scodash.ui.widget.LinearDividerItemDecoration


public final class BindingUtility {
	enum class RecyclerLayout {
		LINEAR_HORIZONTAL,
		LINEAR_VERTICAL,
		GRID
	}

	enum class RecyclerDecoration {
		LINEAR_DIVIDER,
		LINEAR_DIVIDER_LIGHT
	}


	enum class RecyclerAnimator {
		DEFAULT
	}


	@BindingAdapter(value = *arrayOf("recyclerLayout", "numColumns"), requireAll = false)
	fun setRecyclerLayout(recyclerView: RecyclerView, recyclerLayout: RecyclerLayout, numColumns: Int) {
		val layoutManager: RecyclerView.LayoutManager

		if (recyclerLayout == RecyclerLayout.LINEAR_VERTICAL) {
			val linearLayoutManager = LinearLayoutManager(recyclerView.context)
			linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
			layoutManager = linearLayoutManager
		} else if (recyclerLayout == RecyclerLayout.LINEAR_HORIZONTAL) {
			val linearLayoutManager = LinearLayoutManager(recyclerView.context)
			linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
			layoutManager = linearLayoutManager
		} else if (recyclerLayout == RecyclerLayout.GRID) {
			layoutManager = GridLayoutManager(recyclerView.context, numColumns)
		} else {
			throw IllegalArgumentException()
		}

		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
	}


	@BindingAdapter("recyclerDecoration")
	fun setRecyclerDecoration(recyclerView: RecyclerView, recyclerDecoration: RecyclerDecoration) {
		val itemDecoration: RecyclerView.ItemDecoration

		if (recyclerDecoration == RecyclerDecoration.LINEAR_DIVIDER) {
			itemDecoration = LinearDividerItemDecoration(recyclerView.context, null)
		} else if (recyclerDecoration == RecyclerDecoration.LINEAR_DIVIDER_LIGHT) {
			itemDecoration = LinearDividerItemDecoration(recyclerView.context, null, true)
		} else {
			throw IllegalArgumentException()
		}

		recyclerView.addItemDecoration(itemDecoration)
	}


	@BindingAdapter("recyclerAnimator")
	fun setRecyclerAnimator(recyclerView: RecyclerView, recyclerAnimator: RecyclerAnimator) {
		val itemAnimator: RecyclerView.ItemAnimator

		if (recyclerAnimator == RecyclerAnimator.DEFAULT) {
			itemAnimator = DefaultItemAnimator()
		} else {
			throw IllegalArgumentException()
		}

		recyclerView.itemAnimator = itemAnimator
	}


}