package ninja.bryansills.dusty.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow

class RealSettingsDataStore(private val dataStore: DataStore<Settings>) : SettingsDataStore {
    override val settings: Flow<Settings> = dataStore.data
}