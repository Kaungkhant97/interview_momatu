package com.interview.momatu.network

import com.interview.momatu.entity.OutCome
import com.interview.momatu.entity.Photo
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

     fun getPhoto(): Flow<OutCome<List<Photo>>>

}