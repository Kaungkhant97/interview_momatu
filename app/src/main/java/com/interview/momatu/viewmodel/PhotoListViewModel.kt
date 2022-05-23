package com.interview.momatu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.interview.momatu.entity.OutCome
import com.interview.momatu.entity.Photo
import com.interview.momatu.model.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(private val photoRepository: PhotoRepository) :
    ViewModel() {

    val _photolist: LiveData<OutCome<List<Photo>>> =
        photoRepository.getPhoto().asLiveData(Dispatchers.IO);
}