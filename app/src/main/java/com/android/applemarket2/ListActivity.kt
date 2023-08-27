package com.android.applemarket2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.applemarket2.ListData.MyList
import com.android.applemarket2.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postList = MyList.retrievePostList()
        var list = "aaa"
        var aa = "aah"

        binding.recyclerView.adapter = MyAdapter(postList) // commit 추가

        val adapter = MyAdapter(postList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : MyAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                val tile: String = postList[position].title
                Toast.makeText(this@ListActivity," $tile 선택!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}