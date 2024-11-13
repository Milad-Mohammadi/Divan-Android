package ac.divan.util.extensions

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first


suspend fun DataStore<Preferences>.save(key: String, value: String) {
    val dataStoreKey = stringPreferencesKey(key)
    edit { data -> data[dataStoreKey] = value }
}

suspend fun DataStore<Preferences>.save(key: String, value: Int) {
    val dataStoreKey = intPreferencesKey(key)
    edit { data -> data[dataStoreKey] = value }
}

suspend fun DataStore<Preferences>.save(key: String, value: Long) {
    val dataStoreKey = longPreferencesKey(key)
    edit { data -> data[dataStoreKey] = value }
}

suspend fun DataStore<Preferences>.save(key: String, value: Boolean) {
    val dataStoreKey = booleanPreferencesKey(key)
    edit { data -> data[dataStoreKey] = value }
}

suspend fun DataStore<Preferences>.readString(key: String): String? {
    val dataStoreKey = stringPreferencesKey(key)
    val preferences = data.first()
    return preferences[dataStoreKey]
}

suspend fun DataStore<Preferences>.readInt(key: String): Int? {
    val dataStoreKey = intPreferencesKey(key)
    val preferences = data.first()
    return preferences[dataStoreKey]
}

suspend fun DataStore<Preferences>.readLong(key: String): Long? {
    val dataStoreKey = longPreferencesKey(key)
    val preferences = data.first()
    return preferences[dataStoreKey]
}

suspend fun DataStore<Preferences>.readBoolean(key: String): Boolean? {
    val dataStoreKey = booleanPreferencesKey(key)
    val preferences = data.first()
    return preferences[dataStoreKey]
}