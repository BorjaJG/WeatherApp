package com.weatherApp.data.repository

import androidx.lifecycle.LiveData
import com.weatherApp.data.api.WeatherApiService
import com.weatherApp.data.db.WeatherDao
import com.weatherApp.data.db.WeatherEntity
import com.weatherApp.utils.PreferencesManager

/**
 * 🌍 Repositorio principal que gestiona el acceso a los datos meteorológicos.
 *
 * 📡 Se comunica con:
 *  - El servicio remoto [WeatherApiService] para obtener datos desde la API.
 *  - El almacenamiento local (Room) mediante [WeatherDao].
 *  - Las preferencias de usuario mediante [PreferencesManager].
 */
class WeatherRepository(
    private val dao: WeatherDao,                // 💾 Acceso a la base de datos local
    private val api: WeatherApiService,         // 🌐 Servicio para obtener datos del API
    private val prefs: PreferencesManager       // ⚙️ Gestor de preferencias (ciudad y coordenadas)
) {

    /**
     * 📍 Devuelve la ubicación guardada en preferencias.
     *
     * @return Triple con el nombre de la ciudad, latitud y longitud.
     */
    fun getSavedLocation(): Triple<String?, Float?, Float?> {
        val name = prefs.getCityName()
        val latitude = prefs.getLatitude()
        val longitude = prefs.getLongitude()
        return Triple(name, latitude, longitude)
    }

    /**
     * 💾 Guarda una nueva ubicación (nombre, latitud, longitud) en SharedPreferences.
     */
    fun saveLocation(name: String, latitude: Double, longitude: Double) {
        prefs.saveLocation(name, latitude, longitude)
    }

    /**
     * ☁️ Obtiene los datos meteorológicos desde el API y los guarda en la base de datos local.
     *
     * 🔁 Este método elimina los datos antiguos antes de insertar los nuevos.
     */
    suspend fun fetchWeather(latitude: Float?, longitude: Float?) {
        // Llamada al API
        val response = api.getWeather(latitude, longitude)

        // 🔄 Mapeo de la respuesta del API a entidades de base de datos
        val temperatures = response.hourly.temperature_2m.mapIndexed { index, temp ->
            WeatherEntity(
                id = 0, // Room autogenera el ID
                temperature = temp,
                time = response.hourly.time[index]
            )
        }

        // 🧹 Limpiar datos antiguos y guardar los nuevos en la BD
        dao.clearDatabase()
        dao.insertWeatherData(temperatures)
    }

    /**
     * 📊 Devuelve la lista de temperaturas almacenadas en la base de datos local.
     *
     * @return LiveData que contiene la lista de [WeatherEntity].
     */
    fun getWeather(): LiveData<List<WeatherEntity>> {
        return dao.retrieveWeatherData()
    }
}
