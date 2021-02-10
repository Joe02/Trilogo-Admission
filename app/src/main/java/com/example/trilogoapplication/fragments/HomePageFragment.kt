package com.example.trilogoapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.trilogoapplication.R
import com.example.trilogoapplication.adapters.MoviesListAdapter
import com.example.trilogoapplication.databinding.FragmentHomePageBinding
import com.example.trilogoapplication.models.*
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

        val db = context?.let {
            Room.databaseBuilder(
                it,
                RoomDatabase::class.java, "movies-db"
            ).allowMainThreadQueries().build()
        }

        db?.let { checkForPreviousRequests(it) }
        db?.let { setViewClickListeners(it) }
        setUpMoviesRecycler()

        return homeBinding.root
    }

    fun checkForPreviousRequests(db: RoomDatabase) {

        val requestResultDao = db.requestResultDao()
        val request: RequestResult? = requestResultDao.getLastRequest()

        if (request != null) {
            movieRequest = request
        }
    }

    private fun setUpMoviesRecycler() {

        homeBinding.moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        homeBinding.moviesRecyclerView.setHasFixedSize(true)
        moviesListAdapter =
            context?.let { it1 -> MoviesListAdapter(movieRequest.results, it1) }!!

        if(!movieRequest.results.isEmpty()) {
            homeBinding.nextPage.visibility = View.VISIBLE
            homeBinding.previousPage.visibility = View.VISIBLE
        }

        homeBinding.moviesRecyclerView.adapter = moviesListAdapter

    }

    private fun setViewClickListeners(db: RoomDatabase) {

        homeBinding.nextPage.setOnClickListener {
            if (requestIndex != movieRequest.pagesLength) {
                requestIndex += 1
            }

            doRequest(db = db)
        }

        homeBinding.previousPage.setOnClickListener {
            if (requestIndex != 1) {
                requestIndex -= 1
            }
            doRequest(db = db)
        }

        homeBinding.floatingActionButton.setOnClickListener {

            homeBinding.nextPage.visibility = View.VISIBLE
            homeBinding.previousPage.visibility = View.VISIBLE

            doRequest(db = db)
        }
    }

    fun doRequest(db: RoomDatabase) {
        (
                GlobalScope.launch {

                    if (requests[requestIndex].isNullOrEmpty()) {

                        movieRequest = homeViewModel.getMovies(requestIndex)!!

                        activity?.runOnUiThread {

                            moviesListAdapter =
                                context?.let { it1 -> MoviesListAdapter(movieRequest.results, it1) }!!

                            setRecyclerItemClickListener()

                            homeBinding.moviesRecyclerView.adapter = moviesListAdapter

                            requests[requestIndex] = movieRequest.results

                            updateRoom(db = db)

                        }

                    } else {

                        activity?.runOnUiThread {

                            moviesListAdapter =
                                context?.let { it1 -> requests[requestIndex]?.let {
                                    MoviesListAdapter(
                                        it, it1)
                                } }!!

                            setRecyclerItemClickListener()

                            homeBinding.moviesRecyclerView.adapter = moviesListAdapter

                            updateRoom(db = db)
                        }
                    }


                })
    }

    fun updateRoom(db: RoomDatabase) {
        val requestResultDao = db.requestResultDao()
        val request: RequestResult? = requestResultDao.getLastRequest()
        request?.let { requestResultDao.deleteLastRequest(it) }
        requestResultDao.insertRequest(movieRequest)
    }

    fun setRecyclerItemClickListener() {
        moviesListAdapter.onItemClick = {
                movie -> val args = Bundle()
            args.putString("moviePoster", movie.posterPath)
            args.putString("MovieOriginalTitle", movie.originalTitle)
            args.putString("movieTitle", movie.title)
            args.putString("movieOverview", movie.overview)
            args.putString("movieLanguage", movie.movieLanguage)
            movie.popularity?.let { args.putDouble("moviePopularity", it) }

            view?.findNavController()?.navigate(R.id.action_homePageFragment_to_informationPageFragment, args)

        }
    }
}