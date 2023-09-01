package com.android.applemarket2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.applemarket2.ListData.MyList
import java.text.DecimalFormat

class ListDetailActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_list_detail)

        val contactList = MyList.retrievePostList()
        val index = intent.getIntExtra("index", 0)
        val detailImg = findViewById<ImageView>(R.id.detailImg)
        val nickname = findViewById<TextView>(R.id.nickName)
        val title = findViewById<TextView>(R.id.title)
        val place = findViewById<TextView>(R.id.place)
        val subtitle = findViewById<TextView>(R.id.subtitle)
        val price = findViewById<TextView>(R.id.postPrice)

        detailImg.setImageResource(contactList[index].aIcon)
        nickname.text = contactList[index].nickname
        title.text = contactList[index].title
        place.text = contactList[index].place
        subtitle.text = contactList[index].subtitle
        val price2 = formatPrice(contactList[index].price)
        price.text = "${price2}원"
    }

    private fun formatPrice(price: Int): String {
        val decimal = DecimalFormat("#,###")
        return decimal.format(price)
    }
}