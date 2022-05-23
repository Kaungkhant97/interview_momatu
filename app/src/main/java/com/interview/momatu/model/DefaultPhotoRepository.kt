package com.interview.momatu.model

import com.interview.momatu.entity.OutCome
import com.interview.momatu.entity.Photo
import kkt.sai.composetodoapp.model.network.PhotoService
import kkt.sai.composetodoapp.model.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class DefaultPhotoRepository(val remotePhotoDataSource: RemoteDataSource) : PhotoRepository {
    override fun getPhoto(): Flow<OutCome<List<Photo>>> {
        return remotePhotoDataSource.getPhoto();
    }

}
