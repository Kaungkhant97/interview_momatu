package com.interview.momatu.entity


import kotlinx.coroutines.flow.Flow


sealed class OutCome<out R> {

    data class Success<out T>(val data: T) : OutCome<T>()
    data class Error(val exception: Exception) : OutCome<Nothing>()
    object Loading : OutCome<Nothing>()

    override fun toString(): String {

        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }

}

/**
 * `true` if [OutCome] is of type [Success] & holds non-null [Success.data].
 */
val OutCome<*>.succeeded
    get() = this is OutCome.Success && data != null