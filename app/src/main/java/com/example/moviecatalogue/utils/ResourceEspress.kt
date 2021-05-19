package com.example.moviecatalogue.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object ResourceEspress {

    private val RESOURCE: String? = "GLOBAL"
    private val resourceEspress = CountingIdlingResource(RESOURCE)
    fun decrement() {
        resourceEspress.decrement()
    }
    fun increment() {
        resourceEspress.increment()
    }
    fun getResourceEspressid(): IdlingResource {
        return resourceEspress
    }
}