package com.interview.momatu.network

import com.interview.momatu.entity.Photo

import retrofit2.http.GET

interface PhotoService {
    @GET("/v2/list/")
    suspend fun getPhoto(): List<Photo>
}