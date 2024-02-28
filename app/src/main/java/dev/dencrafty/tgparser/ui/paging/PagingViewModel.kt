package dev.dencrafty.tgparser.ui.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dencrafty.tgparser.di.IoDispatcher
import dev.dencrafty.tgparser.data.paging.FeedPagingSource
import dev.dencrafty.tgparser.data.repository.Repository
import dev.dencrafty.tgparser.data.paging.PAGE_SIZE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val repository: Repository,
    private val pagingSource: FeedPagingSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<PagingUIState?> = MutableStateFlow(null)
    val uiState: StateFlow<PagingUIState?> get() = _uiState.asStateFlow()

    /*
    Здесь получаю через репозиторий индекс канала. Обновляю инстанс pagingSource и уведомляю UI для
    дальнейшей работы UI с Paging3 api.
     */
    fun requestChannelInfo(channelId: String) {
        viewModelScope.launch(ioDispatcher) {
            val index = repository.channelSize(channelId)
            try {
                _uiState.update {
                    pagingSource.channel.id = channelId
                    pagingSource.channel.size = index
                    PagingUIState.IndexSuccess
                }
            } catch (ioe: IOException) {
                _uiState.update {
                    PagingUIState.Error(ioe)
                }
            }
        }
    }

    val pagingDataFlow = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        pagingSource
    }.flow
        .cachedIn(viewModelScope)
}