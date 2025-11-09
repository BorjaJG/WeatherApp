package com.weatherApp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * 💾 Interfaz DAO (Data Access Object)
 * Define las operaciones de acceso a los datos meteorológicos almacenados en Room.
 *
 * Room genera automáticamente las implementaciones necesarias a partir de las anotaciones.
 */
@Dao
interface WeatherDao {

    /**
     * 🌤️ Inserta una lista de registros de tipo [WeatherEntity] en la base de datos.
     * Si ya existen datos anteriores, serán reemplazados por los nuevos.
     *
     */
    @Insert
    suspend fun insertWeatherData(data: List<WeatherEntity>)

    /**
     * 📊 Recupera todos los registros almacenados en la tabla `weather_data`,
     * ordenados por hora ascendente (de la mañana a la noche 🌅🌇).
     *
     */
    @Query("SELECT * FROM weather_data ORDER BY hour ASC")
    fun retrieveWeatherData(): LiveData<List<WeatherEntity>>

    /**
     * 🧹 Elimina todos los registros almacenados en la tabla `weather_data`.
     */
    @Query("DELETE FROM weather_data")
    suspend fun clearDatabase()
}
