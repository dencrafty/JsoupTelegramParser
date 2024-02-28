package dev.dencrafty.tgparser.ui.paging

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.dencrafty.tgparser.R
import dev.dencrafty.tgparser.data.model.Feed
import dev.dencrafty.tgparser.databinding.FeedViewholderBinding

class FeedViewHolder(
    private val binding: FeedViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(feed: Feed) {
        binding.apply {
            binding.title.text = feed.index.toString()
            binding.description.text = feed.message

            if (feed.isCommercial) {
                binding.commercialDetected.visibility = View.VISIBLE
                binding.description.setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        R.color.faint_black
                    )
                )
            } else {
                binding.commercialDetected.visibility = View.GONE
                binding.description.setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        R.color.black
                    )
                )
            }

            if (feed.message.isEmpty()) {
                binding.withoutMessage.visibility = View.VISIBLE
            } else {
                binding.withoutMessage.visibility = View.GONE
            }
        }
    }
}