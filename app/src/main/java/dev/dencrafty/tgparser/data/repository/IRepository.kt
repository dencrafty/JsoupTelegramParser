package dev.dencrafty.tgparser.data.repository

import dev.dencrafty.tgparser.data.model.Feed
import dev.dencrafty.tgparser.data.model.FeedChannel

interface IRepository {

    suspend fun channelInfo(channelId: String) : FeedChannel
    // Вытягиваю один пост по имени канала и номеру поста.
    suspend fun singleFeed(channelId: String, feedId: Int) : Feed
    /*
     Вытягиваю условную страницу канала для формирования PagingSource. По факту делается 20 асинхронных
     запросов с помощью singleFeed и формируется список из 20 сообщений.
     */
    suspend fun pageFeed(channelId: String, channelSize: Int, nextPageNumber: Int) : List<Feed>

}