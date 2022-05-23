package com.interview.momatu.network


import android.util.Log
import com.interview.momatu.entity.OutCome
import com.interview.momatu.entity.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.RuntimeException

import kotlin.Exception


class DefaultRemoteDataSource(val photoService: PhotoService) : RemoteDataSource {
    override fun getPhoto(): Flow<OutCome<List<Photo>>> {

        return flow<OutCome<List<Photo>>> {
                emit(OutCome.Loading);

                var photolist = photoService.getPhoto();
                emit(OutCome.Success(photolist))




        }.catch {
            Log.e("asd",it.message.toString());
            emit(OutCome.Error(RuntimeException(it.message)))
        }

    }

}