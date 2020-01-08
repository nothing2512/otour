package com.example.otour.utils

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private val count = CountingIdlingResource(Constants.IDLING)

    fun <T> handle(onHandle: () -> LiveData<T>): LiveData<T> {

        val liveData = MutableLiveData<T>()

        count.increment()
        Handler().postDelayed({
            onHandle.invoke().observeForever { liveData.postValue(it) }
            if (!count.isIdleNow)
                count.decrement()
        }, Constants.SERVICE_LATENCY_IN_MILLIS)

        return liveData
    }
}