package com.example.trilogoapplication.fragments

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.trilogoapplication.R
import com.example.trilogoapplication.databinding.FragmentInformationPageBinding

class InformationPageFragment : Fragment() {

    private lateinit var informationBinding: FragmentInformationPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        informationBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_information_page, container, false)

        loadMovieInformations()

        return informationBinding.root
    }

    @SuppressLint("SetTextI18n")
    fun loadMovieInformations() {

        context?.let {
            Glide.with(it).load("https://image.tmdb.org/t/p/w500/" + arguments?.getString("moviePoster")).into(informationBinding.movieInfoPoster)
        }

        informationBinding.movieInfoOriginalTitle.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
        informationBinding.movieInfoOriginalTitle.text = arguments?.getString("MovieOriginalTitle")

        informationBinding.movieInfoEnglishTitle.typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
        informationBinding.movieInfoEnglishTitle.text = arguments?.getString("movieTitle")

        if(arguments?.getString("movieLanguage") != "en") {
            informationBinding.movieInfoEnglishTitle.visibility = View.GONE
        }

        informationBinding.movieInfoOverView.text = arguments?.getString("movieOverview")
        informationBinding.movieInfoPopularity.text = "Popularity: " + arguments?.getDouble("moviePopularity").toString()

    }
}