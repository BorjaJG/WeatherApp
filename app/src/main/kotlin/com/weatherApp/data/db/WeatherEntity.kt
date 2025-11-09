package com.weatherApp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 🌡️ Entidad que representa un registro de datos meteorológicos en la base de datos local.
 *
 */
@Entity(tableName = "weather_data")
data class WeatherEntity(

    /**
     * 🔑 Identificador único de cada registro.
     * Se genera automáticamente al insertar un nuevo dato.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /**
     * ⏰ Hora del registro en formato texto (por ejemplo, "12:00" o "2025-11-09T15:00").
     */
    val time: String,

    /**
     * 🌡️ Temperatura en grados Celsius correspondiente a la hora indicada.
     */
    val temperature: Double
)
