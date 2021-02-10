package com.example.trilogoapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trilogoapplication.viewmodels.HomePageViewModel
import com.example.trilogoapplication.R
import com.example.trilogoapplication.adapters.MoviesListAdapter
import com.example.trilogoapplication.databinding.FragmentHomePageBinding
import com.example.trilogoapplication.models.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePageFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomePageBinding
    private val homeViewModel: HomePageViewModel by viewModels()
    private lateinit var moviesListAdapter: MoviesListAdapter
    private var moviesList: List<Movie> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        homeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        setViewClickListeners()
        setUpMoviesRecycler()

        return homeBinding.root
    }

    private fun setUpMoviesRecycler() {

        homeBinding.moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        homeBinding.moviesRecyclerView.setHasFixedSize(true)
        moviesListAdapter =
            context?.let { it1 -> MoviesListAdapter(moviesList, it1) }!!
        homeBinding.moviesRecyclerView.adapter = moviesListAdapter

    }

    private fun setViewClickListeners() {
        homeBinding.searchIcon.setOnClickListener {
            ///TODO Adicionar navegação para tela de pesquisa.
        }

        homeBinding.floatingActionButton.setOnClickListener {
            (
                    GlobalScope.launch {

                        moviesList = homeViewModel.getMovies()

                        activity?.runOnUiThread {
                            moviesListAdapter =
                                context?.let { it1 -> MoviesListAdapter(moviesList, it1) }!!
                            homeBinding.moviesRecyclerView.adapter = moviesListAdapter
                        }

                    })
        }
    }
}