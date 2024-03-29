package com.example.listingapp.retrofit

import com.example.listingapp.response.WeatherResponse
import com.example.listingapp.util.BaseApiResponse
import com.example.listingapp.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: ApiHelperImpl
) : BaseApiResponse() {

    suspend fun getWeather(
        lat: Double?,
        long: Double?,
        key: String?
    ): Flow<NetworkResult<WeatherResponse?>> {
        return flow<NetworkResult<WeatherResponse?>> {
            safeApiCall { remoteDataSource.getWeather(lat, long, key) }?.let { emit(it) }
        }.flowOn(Dispatchers.IO)
    }

}
