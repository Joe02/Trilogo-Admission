package com.example.trilogoapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.trilogoapplication.R
import com.example.trilogoapplication.databinding.FragmentSearchPageBinding

class SearchPageFragment: Fragment() {

    private lateinit var searchBinding: FragmentSearchPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        searchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_page, container, false)

        setViewClickListeners()

        return searchBinding.root
    }

    private fun setViewClickListeners() {
        searchBinding.searchIcon.setOnClickListener {
            ///TODO Realizar request de Movies.
        }
    }
}