package com.example.userservice.data.utils

import android.util.Log
import com.example.userservice.domain.core.Resource
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetchFromApi: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
) = flow {
    val data = query().firstOrNull()
    Log.d("xxx data" , "$data")

    val flow = if (shouldFetch(data)) {
        try {
            val fetch = fetchFromApi()
            saveFetchResult(fetch)
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}