package com.example.ideasworld.fragments.favoritesfragment

import android.os.Bundle
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
import com.example.ideasworld.databinding.FragmentFavoritesBinding
import com.example.ideasworld.pojo.PhotoInfo

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val viewModel = ViewModelProvider(
            requireActivity(),
            FavoritesViewModelFactory(requireActivity().application)
        ).get(FavoritesViewModel::class.java)

        viewModel.favoritePhotos.observe(this) {
            val adapter = PhotoAdapter(it)
            recyclerView.adapter = adapter
            adapter.onPhotoClickListener = object: PhotoAdapter.OnPhotoClickListener {
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
