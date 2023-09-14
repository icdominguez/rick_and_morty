package com.icdominguez.core.network

import com.icdominguez.core.common.fakedata.FakeObjects
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

open class ApiEndpointDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        print("The request path is \n \n ${request.path} \n\n why is it failing")

        return when(request.path) {
            "/character?page=1" -> MockResponse()
                .setResponseCode(200)
                .setBody(FakeObjects.charactersPage1)

            "/character?page=1000" -> MockResponse()
                .setResponseCode(404)
                .setBody("{\"error\":\"There is nothing here.\"}")

            "/character/1" -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(FakeObjects.rickDetails)
            }

            "/character/10000" -> {
                MockResponse()
                    .setResponseCode(404)
                    .setBody("{\"error\":\"There is nothing here.\"}")
            }
            else -> {
                MockResponse()
                    .setResponseCode(404)
                    .setBody("{\"error\":\"There is nothing here.\"}")
            }
        }
    }
}