package com.moviecrafters.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.moviecrafters.data.local.AppConstants.MOVIE_ID
import com.moviecrafters.data.local.AppConstants.SESSION_DATASTORE
import com.moviecrafters.data.local.AppConstants.SESSION_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.datastore by preferencesDataStore(SESSION_DATASTORE)

class PreferenceStore(private val context: Context) {

    private val _datastore = context.datastore

    private val sessionToken = stringPreferencesKey(SESSION_TOKEN)
    private val movieIdKey = intPreferencesKey(MOVIE_ID)


    suspend fun clearSessionPrefs() {
        _datastore.edit {
            it.clear()
        }
    }

    suspend fun saveSessionToken(sessionID: String) = _datastore.edit {
        it[sessionToken] = sessionID
    }

    fun getSessionToken(): Flow<String?> = _datastore.data.map {
        it[sessionToken]
    } // again we are returning flow of string

    suspend fun saveMovieId(movieId: Int) = _datastore.edit {
        it[movieIdKey] = movieId
    }
}