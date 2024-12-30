package com.example.weatherapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.Adapters.SearchResultsAdapter
import com.example.weatherapp.Data.LocationSearchResult
import com.example.weatherapp.Interfaces.SearchSuggestionClicked
import com.example.weatherapp.databinding.FragmentSearchResultBinding
import com.google.android.material.transition.MaterialFadeThrough


class SearchResultFragment : Fragment() {

    private lateinit var binding : FragmentSearchResultBinding
    var searchSuggestionClicked : SearchSuggestionClicked? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate layout
        binding = FragmentSearchResultBinding.inflate(layoutInflater,container,false)

        initViews()
        return binding.root
    }

    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.fragmentLSTSearchResult.layoutManager = linearLayoutManager
        enterTransition = MaterialFadeThrough()
    }

    fun loadSearchResult(locationSearchResults : List<LocationSearchResult>){
        val adapter = SearchResultsAdapter(locationSearchResults,searchSuggestionClicked)
        binding.fragmentLSTSearchResult.adapter = adapter

    }

}