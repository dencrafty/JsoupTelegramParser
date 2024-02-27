package dev.dencrafty.tgparser.data

import dev.dencrafty.tgparser.di.IoDispatcher
import dev.dencrafty.tgparser.data.model.Feed
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject
import javax.inject.Singleton

/*
Источник данных. Работает через либу Jsoup с репозиториями https://t.me. Парсит данные из html респонзов
в модель данных Feed.
 */

const val BASE_URL = "https://t.me/s/"
const val LOCAL_URL = "https://t.me/"
const val COMMERCIAL_URL = "https://t.me/+"
const val LINK_CANONICAL = "link[rel=canonical]"
const val HREF_ATTR = "href"
const val CONTENT_ATTR = "content"
const val DESCRIPTION_META = "meta[property=og:description]"

@Singleton
class DataSource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchChannelSize(channelId: String) : Int {
        val channelSize : Int
        withContext(ioDispatcher) {
            val document: Document = Jsoup.connect("$BASE_URL$channelId").get()
            val attr = document.head().select(LINK_CANONICAL).attr(HREF_ATTR)
            val index = attr.indexOf("=")
            channelSize = if (index != -1) {
                attr.substring(index).drop(1).toInt() - 1
            } else {
                0
            }
        }
        return channelSize
    }

    suspend fun fetchFeed(channelId: String, feedId: Int): Feed {
        val message: String
        withContext(ioDispatcher) {
            val document: Document = Jsoup.connect("$LOCAL_URL$channelId/$feedId").get()
            message = document.head().select(DESCRIPTION_META).attr(CONTENT_ATTR)
        }
        return Feed(feedId, message)
    }
}