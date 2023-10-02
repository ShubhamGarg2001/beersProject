package com.example.digimantra.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.digimantra.model.Beer
import com.example.digimantra.utils.AppConstants
import com.example.digimantra.utils.AppConstants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BeerPagingRepository @Inject constructor(
    private val beerApi: BeerApi
) {

    /*
    This function returns the flow of PagingData for Beers.
     */
    fun getBeers(): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { BeerPagingSource(beerApi) }
        ).flow
    }
}