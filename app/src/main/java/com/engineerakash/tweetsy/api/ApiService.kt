package com.engineerakash.tweetsy.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("X-Master-Key: \$2a\$10\$aHfUMtEFQYaSay1PVLviN.CDJXMFu1CXi.ezjIZ90glTYFvd8LQqu")
    @GET("/v3/b/691bdc10ae596e708f5ff9e1")
    suspend fun getCategories(@Query("meta") includeMetaInfo: Boolean = false): Response<List<String>>

    @Headers("X-Master-Key: \$2a\$10\$aHfUMtEFQYaSay1PVLviN.CDJXMFu1CXi.ezjIZ90glTYFvd8LQqu")
    @GET("/v3/b/691bdbf4d0ea881f40ef4065")
    suspend fun getQuotes(
        categoryName: String,
        @Query("meta") includeMetaInfo: Boolean = false
    ): Response<List<Quote>>

}