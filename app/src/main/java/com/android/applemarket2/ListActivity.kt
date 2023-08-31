package com.android.applemarket2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket2.ListData.MyList
import com.android.applemarket2.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postList = MyList.retrievePostList()

        val adapter = MyAdapter(postList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onLongClick(view: View, position: Int) {
                val index: Int = position

                val builder = AlertDialog.Builder(this@ListActivity)
                builder.setTitle("삭제")
                builder.setMessage("삭제하시겠습니까?")
                builder.setIcon(R.mipmap.ic_launcher)

                val listener = DialogInterface.OnClickListener { _, p1 ->
                    when (p1) {
                        DialogInterface.BUTTON_POSITIVE ->
                            MyList.deletePostList(index)
                    }
                    adapter.updateData()
                }

                builder.setPositiveButton("Positive", listener)
                builder.setNegativeButton("Negative", listener)

                builder.show()
            }
            override fun onClick(view: View, position: Int) {
                val index: Int = position
                val detailIntent = Intent(this@ListActivity, ListDetailActivity::class.java)
                detailIntent.putExtra("index", index)
                startActivity(detailIntent)
            }
        }
        binding.alarmButton.setOnClickListener {
            notification()
        }

        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop = true

        // 스크롤 이동시 상당 이동 아이콘이 나와 누르면 최상단으로 이동한다.
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerView.canScrollVertically(-1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    binding.floatingActionButton.startAnimation(fadeOut)
                    binding.floatingActionButton.visibility = View.GONE
                    isTop = true
                } else {
                    if(isTop){
                        binding.floatingActionButton.visibility = View.VISIBLE
                        binding.floatingActionButton.startAnimation(fadeIn)
                        isTop = false
                    }
                }
            }
        })
        binding.floatingActionButton.setOnClickListener{
            binding.recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun exit() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setMessage("종료하시겠습니까?")
        builder.setIcon(R.mipmap.ic_launcher)

        val listener = DialogInterface.OnClickListener { _, p1 ->
            when (p1) {
                DialogInterface.BUTTON_POSITIVE ->
                    finish()
            }
        }

        builder.setPositiveButton("Positive", listener)
        builder.setNegativeButton("Negative", listener)

        builder.show()
    }

    override fun onBackPressed() {
        exit()
    }

    private fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channelId)
        } else {
            builder = NotificationCompat.Builder(this)
        }
        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
        }
        manager.notify(11, builder.build())
    }
}