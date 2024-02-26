package dev.dencrafty.tgparser.data.repository

import dev.dencrafty.tgparser.data.DataSource
import dev.dencrafty.tgparser.data.model.Feed
import dev.dencrafty.tgparser.data.paging.PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val dataSource: DataSource
) : IRepository {

    override suspend fun channelSize(channelId: String): Int {
        return dataSource.fetchChannelSize(channelId)
    }

    override suspend fun singleFeed(channelId: String, feedId: Int): Feed {
        return dataSource.fetchFeed(channelId, feedId)
    }

    /*
    Вся логика формирования одной страницы с сообщениями в диапазоне startRange и endRange.
    Например, если тг канал имеет 99 постов, то 1я страница это посты с 90 по 99, 2я это 80-89 и т.д. по убыванию.
     */
    override suspend fun pageFeed(
        channelId: String,
        channelSize: Int,
        nextPageNumber: Int
    ): List<Feed> {
        val list = mutableListOf<Feed>()
        val startRange = channelSize - PAGE_SIZE * (nextPageNumber - 1)
        val endRange = channelSize - PAGE_SIZE * nextPageNumber + 1
        for (i in startRange downTo endRange) {
            val feed = singleFeed(channelId, i)
            list.add(feed)
        }
        return list
    }
}