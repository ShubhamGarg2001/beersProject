package com.example.digimantra.data

import com.example.digimantra.model.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {
    @GET("v2/beers")
    suspend fun getBeersList(
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): Response<List<Beer>>
}