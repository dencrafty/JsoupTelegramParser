package dev.dencrafty.tgparser.ui.paging

import androidx.recyclerview.widget.RecyclerView
import dev.dencrafty.tgparser.data.model.Feed
import dev.dencrafty.tgparser.databinding.FeedViewholderBinding

class FeedViewHolder (
    private val binding: FeedViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(feed: Feed) {
        binding.apply {
            binding.title.text = feed.index.toString()
            binding.description.text = feed.message
        }
    }
}
