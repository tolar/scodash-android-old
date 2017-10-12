package com.scodash.ui.scoredashitem

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.scodash.R
import com.scodash.databinding.ScoreDashItemBinding
import com.scodash.entity.ScoreDashItem

class ScoreDashItemAdapter(private val scoreDashItemCallback: ScoreDashItemCallback) : RecyclerView.Adapter<ScoreDashItemAdapter.ProductViewHolder>() {

	internal var scoreDashList: List<ScoreDashItem>? = null

	fun setProductList(scoreDashList: List<ScoreDashItem>) {
		if (this.scoreDashList == null) {
			this.scoreDashList = scoreDashList
			notifyItemRangeInserted(0, scoreDashList.size)
		} else {
			val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
				override fun getOldListSize(): Int {
					return this@ScoreDashItemAdapter.scoreDashList!!.size
				}

				override fun getNewListSize(): Int {
					return scoreDashList.size
				}

				override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
					return this@ScoreDashItemAdapter.scoreDashList!![oldItemPosition].name === scoreDashList[newItemPosition].name
				}

				override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
					val newScoreDashItem = scoreDashList[newItemPosition]
					val oldScoreDashItem = this@ScoreDashItemAdapter.scoreDashList!![oldItemPosition]
					return (newScoreDashItem.name == oldScoreDashItem.name
							&& newScoreDashItem.score == oldScoreDashItem.score)
				}
			})
			this.scoreDashList = scoreDashList
			result.dispatchUpdatesTo(this)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
		var binding: ScoreDashItemBinding = DataBindingUtil
				.inflate(LayoutInflater.from(parent.context), R.layout.score_dash_item,
						parent, false)

		binding.scoreDashCallback = scoreDashItemCallback
		return ProductViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
		holder.binding.scoDashItem = (scoreDashList!![position])
		holder.binding.executePendingBindings()
	}

	override fun getItemCount(): Int {
		return if (scoreDashList == null) 0 else scoreDashList!!.size
	}

	class ProductViewHolder(val binding: ScoreDashItemBinding) : RecyclerView.ViewHolder(binding.getRoot())
}