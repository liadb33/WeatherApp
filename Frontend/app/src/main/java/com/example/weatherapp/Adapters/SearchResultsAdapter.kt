package com.example.weatherapp.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Data.LocationSearchResult
import com.example.weatherapp.Interfaces.SearchSuggestionClicked
import com.example.weatherapp.databinding.SearchResultItemBinding

class SearchResultsAdapter(
    private val searchResults: List<LocationSearchResult>,
    private val onSearchSuggestionClicked: SearchSuggestionClicked?
) : RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = SearchResultItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)

        return SearchResultViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    private fun getItem(position: Int) : LocationSearchResult{
        return searchResults[position]
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        with(holder){
            with(searchResults[position]){
                binding.fragmentCityName.text = name
                binding.fragmentCountryName.text = ", $country"
                binding.searchResultItemCARD.setOnClickListener {
                    onSearchSuggestionClicked?.onSearchSuggestionClicked(name,country)
                }
            }
        }
    }


    inner class SearchResultViewHolder(val binding : SearchResultItemBinding) : RecyclerView.ViewHolder(binding.root){

    }



}