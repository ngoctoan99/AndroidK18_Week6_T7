package com.example.androidweek6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.androidweek6.databinding.FragmentDetailBinding
import com.example.androidweek6.model.Movie

class DetailFragment(movie: Movie) : Fragment() {
    lateinit var binding : FragmentDetailBinding
    var movie1 : Movie = movie
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailNameFilm.text = movie1.title
        binding.detailDescribeFilm.text = movie1.overview
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+movie1.posterPath).into(binding.imageDetail)
    }
}