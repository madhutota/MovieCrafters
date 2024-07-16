package com.moviecrafters.domain.repository

//typealias RootError = Error

sealed interface Result<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : Result<D, E>
    data class Error<out D, out E : RootError>(val error: E) : Result<D, E>
    data object Loading : Result<Nothing, Nothing>

}

sealed class RootError {
    data class Network(val message: String) : RootError()
    data class Api(val code: Int, val message: String) : RootError()
    data class Unknown(val message: String) : RootError()
}