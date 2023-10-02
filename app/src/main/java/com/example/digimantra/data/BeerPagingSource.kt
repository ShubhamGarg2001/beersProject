package com.example.digimantra.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.digimantra.model.Beer
import com.example.digimantra.utils.AppConstants.PAGE_SIZE
import java.lang.Exception

/*
Data source responsible for Api call to load Beers
 */
class BeerPagingSource(private val beerApi: BeerApi) : PagingSource<Int, Beer>() {

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    /*
    Actual load method which does api call with page no and PAGE_SIZE which is set to 10.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        return try {
            val page = params.key ?: 1
            val response = beerApi.getBeersList(page, PAGE_SIZE)
            if (response.isSuccessful) {
                val beersList = response.body() ?: listOf()
                LoadResult.Page(
                    data = beersList,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (beersList.isEmpty()) null else page + 1
                )
            } else
                LoadResult.Error(Throwable(response.errorBody()?.string()))


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}