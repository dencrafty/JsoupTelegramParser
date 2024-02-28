package dev.dencrafty.tgparser.data.model

import dev.dencrafty.tgparser.data.COMMERCIAL_URL

/*
Сейчас вытягивается из одного тг поста его номер, сообщение и локально проверка на рекламный пост.
 */
data class Feed(
    val index: Int,
    val message: String
) {
    val isCommercial: Boolean = message.contains(COMMERCIAL_URL)
}
