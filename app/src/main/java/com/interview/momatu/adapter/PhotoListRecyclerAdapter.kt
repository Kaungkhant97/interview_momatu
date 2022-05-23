package com.interview.momatu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.interview.momatu.R
import com.interview.momatu.entity.Photo

class PhotoListRecyclerAdapter(val photolist: ArrayList<Photo>) :
    RecyclerView.Adapter<PhotoListRecyclerAdapter.PhotoListViewHolder>() {

    class PhotoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var imageview: ImageView?

        init {

            imageview = itemView.findViewById(R.id.photo_imageview);

        }

        fun bind(photo: Photo) {
            //imageview.

            Glide.with(itemView)
                .load(photo.downloadURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.defaultimage)
                .signature(ObjectKey(photo.downloadURL))
                .into(imageview!!)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.photo_viewholder, parent, false);
        return PhotoListViewHolder(view);
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {

        holder.bind(photolist.get(position));
    }

    override fun getItemCount(): Int {
        return photolist.size;
    }

    fun bind(data: List<Photo>) {
        photolist.clear()
        photolist.addAll(data);
        notifyDataSetChanged()
    }
}