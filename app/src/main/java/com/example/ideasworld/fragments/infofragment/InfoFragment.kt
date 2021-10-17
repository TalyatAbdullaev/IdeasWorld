package com.example.ideasworld.fragments.infofragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.ideasworld.databinding.FragmentInfoBinding
import com.example.ideasworld.pojo.PhotoInfo

class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            InfoViewModelFactory(requireActivity().application)
        ).get(InfoViewModel::class.java)

        val id: String = arguments?.get("id").toString()
        var photoInfo: PhotoInfo? = null
        if (viewModel.photoExist(id)) {
            photoInfo = viewModel.loadPhotoInfoFromDB(id)
            setImageInfo(photoInfo)
        } else {
            viewModel.loadPhotoInfo(id)
            viewModel.photoInfo.observe(this) {
                photoInfo = it
                setImageInfo(photoInfo)
            }
        }

        binding.chbFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                viewModel.addToFavorite(photoInfo!!)
            else
                viewModel.deleteFromFavorite(photoInfo!!)
        }
    }

    private fun setImageInfo(photoInfo: PhotoInfo?) {
        photoInfo?.let {
            val photoId: String = photoInfo.id
            val photoUrl: String = photoInfo.photoUrls.raw
            val photoLikes: Int = photoInfo.likes
            val photoDescription: String? = photoInfo.description

            binding.tvLikesNum.text = photoLikes.toString()

            if (photoDescription.isNullOrEmpty())
                binding.tvDescription.visibility = View.GONE
            else
                binding.tvDescription.text = photoDescription

            binding.chbFavorite.isChecked = viewModel.photoExist(photoId)

            setImage(photoUrl, binding.ivPhoto, binding.progressBar)
        }

    }

    private fun setImage(
        photoUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        progressBar.visibility = View.VISIBLE

        Glide.with(requireActivity().applicationContext)
            .asBitmap()
            .load(photoUrl)
            .centerCrop()
            .addListener(object : RequestListener<Bitmap> {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}