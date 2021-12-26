package ninja.bryansills.dusty.datastore

import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    val settings: Flow<Settings>
}

