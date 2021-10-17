package com.example.ideasworld.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.ideasworld.databinding.PhotoItemBinding
import com.example.ideasworld.pojo.PhotoInfo

class PhotoAdapter(private val photos: List<PhotoInfo>):
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>(){
    var onPhotoClickListener: OnPhotoClickListener? = null

    inner class PhotoViewHolder(val binding: PhotoItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        with(holder){
            with(photos[position]){
                val imageView: ImageView = binding.imageView
                val photoUrl: String = this.photoUrls.thumb
                val progressBar: ProgressBar = binding.progressBar
                //val photoHeight: Int = this.height
                //val photoWidth: Int = this.width
                setPhoto(holder.itemView.context, imageView, photoUrl, progressBar)
            }
        }

        holder.itemView.setOnClickListener {
            onPhotoClickListener?.onPhotoClick(photos[position])
        }
    }

    override fun getItemCount(): Int = photos.size

    interface OnPhotoClickListener {
        fun onPhotoClick(photo: PhotoInfo)
    }

    private fun setPhoto(context: Context, imageView: ImageView, photoUrl: String, progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE

        Glide.with(context)
            .asBitmap()
            .load(photoUrl)
            .addListener(object: RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

            })
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
}