package dev.dencrafty.tgparser.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import dev.dencrafty.tgparser.data.model.Feed
import dev.dencrafty.tgparser.databinding.FeedViewholderBinding

class FeedAdapter(diffCallback: DiffUtil.ItemCallback<Feed>) :
    PagingDataAdapter<Feed, FeedViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedViewHolder {
        return FeedViewHolder(
            FeedViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }
}

object UserComparator : DiffUtil.ItemCallback<Feed>() {
    override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem.index == newItem.index
    }

    override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem == newItem
    }
}