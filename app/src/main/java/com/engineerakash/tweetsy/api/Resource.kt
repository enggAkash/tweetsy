package com.engineerakash.tweetsy.api

class Resource {
    sealed class Data<T>(data: T)
    sealed class Error<T>(error: T)
    object Loading
}