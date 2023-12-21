package com.test.faurecia.data.remote

import com.test.faurecia.data.remote.model.ListAppsResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/6/bulkRequest/api_list/listApps")
    suspend fun listApps(): ListAppsResponseDTO

}