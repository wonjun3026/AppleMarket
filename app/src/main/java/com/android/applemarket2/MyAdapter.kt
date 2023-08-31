package com.android.applemarket2

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket2.ListData.MyItem
import com.android.applemarket2.databinding.ItemRecyclerviewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.text.DecimalFormat

class MyAdapter(private val mItems: List<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {
    interface ItemClick {
        fun onLongClick(view: View, position: Int)
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemClick?.onLongClick(it, position)
            true
        }
        Glide.with(holder.itemView.context)
            .load(mItems[position].aIcon)
            .transform(MultiTransformation(FitCenter(), RoundedCorners(40)))
            .into(holder.iconImageView)
        holder.title.text = mItems[position].title
        holder.place.text = mItems[position].place
        holder.liked.text = mItems[position].liked.toString()
        holder.chat.text = mItems[position].chat.toString()
        val price2 = formatPrice(mItems[position].price)
        holder.price.text = "${price2}원"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    private fun formatPrice(price: Int): String{
        val decimal = DecimalFormat("#,###")
        return decimal.format(price)
    }

    inner class Holder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val title = binding.title
        val place = binding.place
        val price = binding.price
        val liked = binding.liked
        val chat = binding.chat
    }

    fun updateData(){
        notifyDataSetChanged()
    }
}