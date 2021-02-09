package com.example.trilogoapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.trilogoapplication.HomePageViewModel
import com.example.trilogoapplication.R
import com.example.trilogoapplication.databinding.FragmentHomePageBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePageFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomePageBinding
    private val homeViewModel: HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        homeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        setViewClickListeners()

        return homeBinding.root
    }

    private fun setViewClickListeners() {
        homeBinding.searchIcon.setOnClickListener {
            ///TODO Adicionar navegação para tela de pesquisa.
        }

        homeBinding.floatingActionButton.setOnClickListener {
            GlobalScope.launch {
                homeViewModel.getMovies()

                activity?.runOnUiThread {
                    //TODO Criar livedata na viewModel.
                }
            }
        }
    }
}