package com.example.digimantra.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.digimantra.data.BeerPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    beerPagingRepository: BeerPagingRepository
) : ViewModel() {

    /*
    Flow for collecting data from repository
     */
    val beersListFlow = beerPagingRepository.getBeers().cachedIn(viewModelScope)
}