package dev.dencrafty.tgparser.data.model

/*
Сейчас вытягивается из одного тг поста его номер и сообщение.
 */
data class Feed(
    val index: Int,
    val message: String
)
