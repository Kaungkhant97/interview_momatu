package com.interview.momatu.model

import com.interview.momatu.entity.OutCome
import com.interview.momatu.entity.Photo
import kkt.sai.composetodoapp.model.network.PhotoService
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    fun getPhoto(): Flow<OutCome<List<Photo>>>
}