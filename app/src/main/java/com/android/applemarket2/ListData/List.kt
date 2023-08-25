package com.android.applemarket2.ListData

import com.android.applemarket2.R

object MyList {
    var postList = mutableListOf(
        MyItem(
            R.drawable.ic_post_1,
            "산진 한달된 선풍기 팝니다",
            "대현동",
            "1000",
            "이사 가서 필요가 없어졌어요 급하게 내놓습니다"
            ),
        MyItem(
            R.drawable.ic_post_2,
            "산진 한달된 선풍기 팝니다",
            "대현동",
            "1000",
            "이사 가서 필요가 없어졌어요 급하게 내놓습니다"
        ),
        MyItem(
            R.drawable.ic_post_3,
            "산진 한달된 선풍기 팝니다",
            "대현동",
            "1000",
            "이사 가서 필요가 없어졌어요 급하게 내놓습니다"
        ),
        MyItem(
            R.drawable.ic_post_4,
            "산진 한달된 선풍기 팝니다",
            "대현동",
            "1000",
            "이사 가서 필요가 없어졌어요 급하게 내놓습니다"
        ),
        MyItem(
            R.drawable.ic_post_5,
            "산진 한달된 선풍기 팝니다",
            "대현동",
            "1000",
            "이사 가서 필요가 없어졌어요 급하게 내놓습니다"
        ),
        MyItem(
            R.drawable.ic_post_6,
            "산진 한달된 선풍기 팝니다",
            "대현동",
            "1000",
            "이사 가서 필요가 없어졌어요 급하게 내놓습니다"
        ),
        MyItem(
            R.drawable.ic_post_7,
            "산진 한달된 선풍기 팝니다",
            "대현동",
            "1000",
            "이사 가서 필요가 없어졌어요 급하게 내놓습니다"
        ),
        MyItem(
            R.drawable.ic_post_8,
            "산진 한달된 선풍기 팝니다",
            "대현동",
            "1000",
            "이사 가서 필요가 없어졌어요 급하게 내놓습니다"
        ),
        MyItem(
            R.drawable.ic_post_9,
            "산진 한달된 선풍기 팝니다",
            "대현동",
            "1000",
            "이사 가서 필요가 없어졌어요 급하게 내놓습니다"
        )
    )

    fun retrievePostList(): List<MyItem> {
        return postList
    }
}