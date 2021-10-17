package com.example.ideasworld.fragments.photosfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ideasworld.adapters.PhotoAdapter
import com.example.ideasworld.R
import com.example.ideasworld.databinding.FragmentPhotosBinding
import com.example.ideasworld.pojo.PhotoInfo

class PhotosFragment : Fragment() {
    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!
    private var photos: List<PhotoInfo> = listOf()
    private lateinit var viewModel: PhotosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel = ViewModelProvider(
            requireActivity(),
            PhotosViewModelFactory(requireActivity().application)
        ).get(PhotosViewModel::class.java)

        val swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.photos.value = listOf()
            viewModel.loadImages()
            swipeRefreshLayout.isRefreshing = false
        }

        viewModel.loadImages()
        viewModel.photos.observe(this) {
            val adapter = PhotoAdapter(it)
            binding.recyclerView.adapter = adapter
            adapter.onPhotoClickListener = object : PhotoAdapter.OnPhotoClickListener {
                override fun onPhotoClick(photo: PhotoInfo) {
                    val photoId = photo.id
                    val bundle = bundleOf("id" to photoId)
                    findNavController().navigate(R.id.infoFragment, bundle)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}