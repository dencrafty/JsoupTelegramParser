package dev.dencrafty.tgparser.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.dencrafty.tgparser.data.model.Feed
import dev.dencrafty.tgparser.data.model.FeedChannel
import dev.dencrafty.tgparser.data.repository.Repository
import javax.inject.Inject

const val PAGE_SIZE = 20

/*
Здесь формирую данные для отображения в recycler view.
 */

class FeedPagingSource @Inject constructor(
    private val repository: Repository
) : PagingSource<Int, Feed>() {

    var channel = FeedChannel()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Feed> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response =
                repository.pageFeed(channel.id, channel.size, nextPageNumber)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.size < PAGE_SIZE) null else (nextPageNumber + 1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Feed>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}