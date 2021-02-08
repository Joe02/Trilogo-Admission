package com.example.trilogoapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.trilogoapplication.R
import com.example.trilogoapplication.databinding.FragmentHomePageBinding

class HomePageFragment: Fragment() {

    private lateinit var homeBinding: FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        setViewClickListeners()

        return homeBinding.root
    }

    private fun setViewClickListeners() {
        homeBinding.searchIcon.setOnClickListener {
            ///TODO Adicionar navegação para tela de pesquisa.
        }

        homeBinding.floatingActionButton.setOnClickListener {
            ///TODO Adicionar request.
        }
    }
}