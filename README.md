

---

## Descripción general

En esta práctica, deberás implementar varios mecanismos de persistencia local en una aplicación Android que muestra datos meteorológicos obtenidos del API de [Open-Meteo](https://open-meteo.com/).

La empresa para la que trabajas quiere comercializar una aplicación móvil que muestre de forma gráfica el tiempo en cualquier lugar del mundo.  
El resto del equipo ya ha implementado las interfaces gráficas y la mayor parte de la lógica de la aplicación, por lo que **solo tendrás que encargarte de la persistencia**.

---

## Requisitos

### 1. Guardar la ubicación en `SharedPreferences` (clase `PreferencesManager`)

- Al introducir una ciudad y sus coordenadas en `LocationFragment`, al pulsar el botón de **guardar**, los cambios deben almacenarse en un fichero de `SharedPreferences`.  
- Actualmente no existe dicho fichero, y las funciones de `PreferencesManager` no hacen nada o devuelven valores por defecto.

---

### 2. Almacenar temperaturas en Room  
*(clases `WeatherDatabase`, `WeatherEntity` e interfaz `WeatherDao`)*

- Cada vez que `WeatherRepository` reciba datos del API (el mapeo ya está implementado), se deben **eliminar todos los datos de la tabla `weather_data`** en la base de datos SQLite `weather_db`.  
- Luego, se debe **insertar el listado de `WeatherEntity`** mapeado a partir de la respuesta del API.  
- Este acceso debe realizarse utilizando **Room**.

---

### 3. Obtener temperaturas desde Room  
*(interfaz `WeatherDao`)*

- Debe ser posible obtener el listado de `WeatherEntity` almacenadas en la base de datos, **ordenadas por hora de menor a mayor**.  
- De esta forma, el gráfico en `TemperatureChartFragment` mostrará correctamente la evolución de las temperaturas a lo largo del día.

---

## Estructura de la aplicación

Las clases e interfaces que debes modificar o implementar son:

- `PreferencesManager`
- `WeatherDao`
- `WeatherEntity`
- `WeatherDatabase`

Las clases `ApplicationContext` y `WeatherRepository` contienen pequeñas modificaciones temporales que deberás revertir una vez completes las implementaciones anteriores.

---

## Resultados de aprendizaje (RA)

- **RA 1** – Desarrolla aplicaciones que gestionan información almacenada en ficheros, identificando el campo de aplicación y utilizando clases específicas.  
- **RA 2** – Desarrolla aplicaciones que gestionan información almacenada en bases de datos relacionales, identificando y utilizando mecanismos de conexión.  
- **RA 3** – Gestiona la persistencia de los datos identificando herramientas de mapeo objeto-relacional (ORM) y desarrollando aplicaciones que las utilizan.

---

## Rúbrica de evaluación

| Criterio | Excelente (4) | Adecuado (3) | Mejorable (2) | Insuficiente (1) | No presentado (0) |
|-----------|----------------|---------------|----------------|-------------------|-------------------|
| **1. Implementación de `PreferencesManager`** | Implementa correctamente lectura y escritura en `SharedPreferences`. Permite modificar y recuperar valores sin errores. | Lee y guarda valores correctamente, pero con pequeños fallos o repeticiones. | Guarda o lee parcialmente los datos; errores de contexto. | No guarda ni recupera valores reales o la clase no compila. | - |
| **2. Definición de `WeatherEntity`** | Define correctamente campos y anotaciones de Room. Estructura coincide con la BD. | Entidad funcional pero con algún error menor. | Entidad incompleta o con anotaciones incorrectas. | Guarda datos parcialmente o con errores. | Entidad ausente o clase no compila. |
| **3. Implementación de `WeatherDao`** | Incluye correctamente métodos para insertar, eliminar y obtener temperaturas con anotaciones adecuadas. | Métodos principales con pequeños fallos (por ejemplo, sin ordenar). | Métodos incompletos o con anotaciones incorrectas. | Métodos ausentes o errores que impiden la compilación. | - |
| **4. Configuración de `WeatherDatabase`** | Configura correctamente Room y devuelve una instancia única (singleton). | Funcional pero sin patrón singleton o con parámetros erróneos. | Funcional pero con errores puntuales. | No implementa correctamente Room o no compila. | - |
"""

# Crear el archivo .md
output_path = Path("/mnt/data/Enunciado_practica_1.md")
output_path.write_text(markdown_content, encoding="utf-8")

output_path
