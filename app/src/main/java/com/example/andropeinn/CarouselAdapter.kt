package com.example.andropeinn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

class CarouselAdapter(private val context: Context, private val images:List<Int>):RecyclerView.Adapter<CarouselAdapter.ImageViewHolder>() {


    class ImageViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {

        val imageView:ImageView=itemview.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselAdapter.ImageViewHolder {

        val view=LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselAdapter.ImageViewHolder, position: Int) {
        val imagesRes=images[position]
        holder.imageView.setImageResource(imagesRes)
    }

    override fun getItemCount(): Int {
      return images.size
    }


}