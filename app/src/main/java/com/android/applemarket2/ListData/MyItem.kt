package com.android.applemarket2.ListData

data class MyItem( //게시글 데이터
    val nickname: String,
    val aIcon: Int,
    val title: String,
    val place: String,
    val price: Int,
    val subtitle: String,
    var liked: Int,
    var chat: Int
)