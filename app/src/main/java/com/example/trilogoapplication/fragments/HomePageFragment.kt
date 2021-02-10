package com.example.trilogoapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trilogoapplication.R
import com.example.trilogoapplication.adapters.MoviesListAdapter
import com.example.trilogoapplication.databinding.FragmentHomePageBinding
import com.example.trilogoapplication.models.Movie
import com.example.trilogoapplication.models.RequestResult
import com.example.trilogoapplication.viewmodels.HomePageViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePageFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomePageBinding
    private val homeViewModel: HomePageViewModel by viewModels()
    private lateinit var moviesListAdapter: MoviesListAdapter
    private var movieRequest: RequestResult = RequestResult(results = listOf(), pagesLength = 0)
    private var requestIndex = 1
    private var requests : MutableMap<Int, List<Movie>> = mutableMapOf()

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
            context?.let { it1 -> MoviesListAdapter(movieRequest.results, it1) }!!
        homeBinding.moviesRecyclerView.adapter = moviesListAdapter

    }

    private fun setViewClickListeners() {

        homeBinding.nextPage.setOnClickListener {
            if (requestIndex != movieRequest.pagesLength) {
                requestIndex += 1
            }

            doRequest()
        }

        homeBinding.previousPage.setOnClickListener {
            if (requestIndex != 1) {
                requestIndex -= 1
            }
            doRequest()
        }

        homeBinding.floatingActionButton.setOnClickListener {

            homeBinding.nextPage.visibility = View.VISIBLE
            homeBinding.previousPage.visibility = View.VISIBLE

            doRequest()
        }
    }

    fun doRequest() {
        (
                GlobalScope.launch {

                    if (requests[requestIndex].isNullOrEmpty()) {

                        movieRequest = homeViewModel.getMovies(requestIndex)!!

                        activity?.runOnUiThread {

                            moviesListAdapter =
                                context?.let { it1 -> MoviesListAdapter(movieRequest.results, it1) }!!

                            homeBinding.moviesRecyclerView.adapter = moviesListAdapter

                            requests[requestIndex] = movieRequest.results
                        }

                    } else {

                        activity?.runOnUiThread {

                            moviesListAdapter =
                                context?.let { it1 -> requests[requestIndex]?.let {
                                    MoviesListAdapter(
                                        it, it1)
                                } }!!

                            homeBinding.moviesRecyclerView.adapter = moviesListAdapter
                        }
                    }


                })
    }
}