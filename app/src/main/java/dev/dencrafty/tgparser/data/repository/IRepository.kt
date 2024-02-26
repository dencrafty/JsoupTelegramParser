package dev.dencrafty.tgparser.data.repository

import dev.dencrafty.tgparser.data.model.Feed

interface IRepository {
    // Узнаю общее количество выпущенных постов на канале с начала основания. Посты в тг нумеруются от 1 до N.
    suspend fun channelSize(channelId: String) : Int
    // Вытягиваю один пост по имени канала и номеру поста.
    suspend fun singleFeed(channelId: String, feedId: Int) : Feed
    /*
     Вытягиваю условную страницу канала для формирования PagingSource. По факту делается 20 асинхронных
     запросов с помощью singleFeed и формируется список из 20 сообщений.
     */
    suspend fun pageFeed(channelId: String, channelSize: Int, nextPageNumber: Int) : List<Feed>
}