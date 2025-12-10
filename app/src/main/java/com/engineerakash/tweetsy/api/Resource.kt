package com.engineerakash.tweetsy.api

sealed interface Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>
    data class Error(val errorMessage: String) : Resource<Nothing>
    object Loading : Resource<Nothing>
}