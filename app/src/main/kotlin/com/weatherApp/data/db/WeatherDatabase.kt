package com.weatherApp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * 🏗️ Clase principal que representa la base de datos local de la aplicación.
 *
 */
@Database(entities = [WeatherEntity::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    /**
     * 🔗 Proporciona acceso al DAO (Data Access Object) para realizar operaciones sobre la base de datos.
     */
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        /**
         * 💡 Devuelve una instancia única de la base de datos [WeatherDatabase].
         * Si no existe, la crea usando [Room.databaseBuilder].
         *
         */
        fun getDatabase(context: Context): WeatherDatabase {
            // 🔒 Bloque sincronizado para evitar múltiples instancias en hilos diferentes.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_db" // 🗄️ Nombre del archivo físico de la base de datos.
                )
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
