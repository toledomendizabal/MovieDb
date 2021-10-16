package com.test.moviedb.presentatiion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.moviedb.R
import com.test.moviedb.core.domain.Response
import com.test.moviedb.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.os.Build

import android.annotation.TargetApi

import android.webkit.PermissionRequest

import android.webkit.WebChromeClient
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var movieId:Int = 0
    private var isMovie:Boolean = true
    private val viewModel by viewModel<DetailViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false).apply {


        }
        viewModel.loadDetail(movieId, isMovie)
        var name:String="";
        viewModel.detail.observe(viewLifecycleOwner){
            when (it) {
                is Response.Loading -> {
                    binding.progressBar3.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    if (isMovie){
                        name=it.data.original_title.toString()
                    }else{
                        name=it.data.original_name.toString()
                    }
                    binding.progressBar3.visibility = View.GONE
                    binding.title.text = name
                    Glide.with(binding.poster)
                        .load("https://image.tmdb.org/t/p/original${it.data.poster_path}")
                        .placeholder(R.drawable.broken)
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(binding.poster)
                    binding.overview.text=it.data.overview
                }
                is Response.Error -> {
                    binding.progressBar3.visibility = View.GONE
                    Toast.makeText(requireContext(), "We couldn't load the data", Toast.LENGTH_LONG).show()
                }
                else -> {
                    binding.progressBar3.visibility = View.GONE
                }
            }
        }
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root

    }

    companion object{
        fun newInstance(movieId:Int, isMovie:Boolean) = DetailFragment().apply {
            this.movieId = movieId
            this.isMovie = isMovie
        }
    }

}