package dev.dencrafty.tgparser.ui.paging

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.dencrafty.tgparser.R
import dev.dencrafty.tgparser.data.TGSTAT_URL
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
                binding.deletedDetected.visibility = View.VISIBLE
                binding.deletedDetected.setOnClickListener {
                    root.context.startActivity(
                        Intent(
                            ACTION_VIEW,
                            Uri.parse("$TGSTAT_URL${feed.channelId}/${feed.index}")
                        )
                    )
                }
            } else {
                binding.deletedDetected.visibility = View.GONE
            }
        }
    }
}
