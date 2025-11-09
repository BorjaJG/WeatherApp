package com.weatherApp.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * 💾 Clase que gestiona el almacenamiento y recuperación de datos de configuración del usuario
 * mediante SharedPreferences. En este caso, guarda la ubicación seleccionada 🌍
 * (nombre de la ciudad, latitud y longitud).
 *
 * 📂 SharedPreferences permite guardar pares clave-valor de forma persistente
 * dentro del almacenamiento interno de la aplicación.
 */
class PreferencesManager(context: Context) {

    companion object {
        // 🗂️ Nombre del archivo de preferencias que se creará en el almacenamiento interno.
        private const val PREFS_NAME = "weather_prefs"

        // 🔑 Claves que se usarán para acceder a cada valor guardado.
        private const val KEY_CITY_NAME = "key_city_name"
        private const val KEY_LATITUDE = "key_latitude"
        private const val KEY_LONGITUDE = "key_longitude"
    }

    /**
     * ⚙️ Se obtiene la instancia de SharedPreferences asociada al archivo [PREFS_NAME].
     * 🔒 MODE_PRIVATE significa que solo esta aplicación puede acceder a este archivo.
     */
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * 💾 Guarda la ubicación del usuario en SharedPreferences.
     *
     * @param city 🏙️ Nombre de la ciudad seleccionada.
     * @param latitude 📍 Latitud en formato Double (convertida internamente a Float).
     * @param longitude 📍 Longitud en formato Double (convertida internamente a Float).
     *
     * ⚠️ SharedPreferences no permite guardar valores Double, por eso se convierten a Float.
     * ⚡ Se usa [apply()] para guardar los cambios de forma asíncrona (sin bloquear la UI).
     */
    fun saveLocation(city: String, latitude: Double, longitude: Double) {
        prefs.edit().apply {
            putString(KEY_CITY_NAME, city)             // 🏙️ Guarda el nombre de la ciudad
            putFloat(KEY_LATITUDE, latitude.toFloat()) // 🌡️ Guarda la latitud como Float
            putFloat(KEY_LONGITUDE, longitude.toFloat()) // 🌡️ Guarda la longitud como Float
            apply() // ✅ Aplica los cambios de forma asíncrona
        }
    }

    /**
     * 🔍 Devuelve el nombre de la ciudad almacenada en preferencias.
     *
     * @return 🏙️ El nombre de la ciudad si existe, o null si nunca se ha guardado.
     */
    fun getCityName(): String? {
        return prefs.getString(KEY_CITY_NAME, null)
    }

    /**
     * 📍 Devuelve la latitud almacenada.
     *
     * @return 🌡️ La latitud si existe; si no se ha guardado ninguna, devuelve 0.0f.
     *
     * 💡 Nota: En esta versión se devuelve 0.0f como valor por defecto.
     * Si prefieres devolver null cuando no exista, puedes usar Float.MIN_VALUE como valor centinela.
     */
    fun getLatitude(): Float? {
        return prefs.getFloat(KEY_LATITUDE, 0.0f)
    }

    /**
     * 📍 Devuelve la longitud almacenada.
     *
     * @return 🌡️ La longitud si existe; si no se ha guardado ninguna, devuelve 0.0f.
     *
     * 💬 Igual que en getLatitude(), podrías devolver null si no hay valor guardado.
     */
    fun getLongitude(): Float? {
        return prefs.getFloat(KEY_LONGITUDE, 0.0f)
    }
}
