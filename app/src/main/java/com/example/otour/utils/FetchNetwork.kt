package com.example.otour.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.otour.data.source.ApiEmptyResponse
import com.example.otour.data.source.ApiErrorResponse
import com.example.otour.data.source.ApiResponse
import com.example.otour.data.source.ApiSuccessResponse
import com.example.otour.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Suppress("LeakingThis")
abstract class FetchNetwork<DATA_TYPE>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<DATA_TYPE>>()

    @MainThread
    private fun setValue(newValue: Resource<DATA_TYPE>) {
        if (result.value != newValue) result.value = newValue
    }

    protected open fun onFetchFailed() {}

    suspend fun asLiveData() = withContext(Dispatchers.IO) {

        appExecutors.mainThread.execute {

            result.postValue(Resource.loading())

            val apiResponse = EspressoIdlingResource.handle { createCall() }

            result.addSource(apiResponse) { response ->

                result.removeSource(apiResponse)

                when (response) {

                    is ApiSuccessResponse -> {
                        appExecutors.diskIO.execute {
                            appExecutors.mainThread.execute {
                                setValue(Resource.success(processResponse(response)))
                            }
                        }

                    }
                    is ApiEmptyResponse -> {
                        appExecutors.mainThread.execute {
                            setValue(Resource.success(null))
                        }
                    }
                    is ApiErrorResponse -> {
                        onFetchFailed()
                        setValue(Resource.error(response.errorMessage, null))
                    }
                }
            }
        }
        result as LiveData<Resource<DATA_TYPE>>
    }

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<DATA_TYPE>) = response.body

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<DATA_TYPE>>

}