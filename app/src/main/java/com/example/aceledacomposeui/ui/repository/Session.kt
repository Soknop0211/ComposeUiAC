package com.example.aceledacomposeui.ui.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Session @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        const val DATA = "Data"
        private const val LIST_HOME = "ListHomeScreen"
        val mListHome = stringPreferencesKey(LIST_HOME)
    }

    fun getHomeScreenList(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[mListHome] ?: ""
        }
    }

    suspend fun setHomeScreenList(homeList: String) {
        dataStore.edit { preference ->
            preference[mListHome] = homeList
        }
    }
}