package com.engineerakash.tweetsy.repository

import com.engineerakash.tweetsy.api.ApiService
import com.engineerakash.tweetsy.api.Quote
import com.engineerakash.tweetsy.api.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val apiService: ApiService) {

    fun getCategories(): Flow<Resource<List<String>>> {
        return flow {
            emit(Resource.Loading)
            val categoryResponse = apiService.getCategories()

            try {
                if (categoryResponse.isSuccessful) {
                    if (categoryResponse.body() != null) {
                        emit(Resource.Success(categoryResponse.body()!!))
                    } else {
                        emit(Resource.Error("Empty Data"))
                    }
                } else {
                    emit(Resource.Error(categoryResponse.errorBody().toString()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Something is not right"))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getQuotes(categoryName: String): Flow<Resource<List<Quote>>> {
        return flow {
            emit(Resource.Loading)
            val quotesResponse = apiService.getQuotes(categoryName)

            try {
                if (quotesResponse.isSuccessful) {
                    if (quotesResponse.body() != null) {
                        emit(
                            Resource.Success(
                                quotesResponse.body()!!
                                    .filter {
                                        it.category.equals(
                                            categoryName,
                                            true
                                        )
                                    }
                            )
                        )
                    } else {
                        emit(Resource.Error("Empty Data"))
                    }
                } else {
                    emit(Resource.Error(quotesResponse.errorBody().toString()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Something is not right"))
            }
        }
    }

}