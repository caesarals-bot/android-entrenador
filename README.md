# Entrenador Virtual

**Entrenador Virtual** es una sencilla aplicación de Android diseñada para ayudarte a configurar y registrar temporalmente tus sesiones de entrenamiento. Ingresa el deporte que vas a practicar y el tiempo de tu sesión, guárdalo y luego visualiza la información cuando lo necesites.

## Características

*   **Configuración de Entrenamiento:**
    *   Ingresa el nombre del deporte o actividad física.
    *   Especifica la duración del entrenamiento en minutos.
*   **Guardado Temporal:**
    *   Guarda la configuración de tu entrenamiento actual.
    *   El formulario se limpia después de guardar para facilitar una nueva entrada.
*   **Visualización de Entrenamiento:**
    *   Muestra la información del último entrenamiento guardado (deporte y tiempo).
    *   La información se presenta tanto en un mensaje emergente (Toast) como directamente en la pantalla.
    *   Los detalles del entrenamiento también se registran en la consola del desarrollador (Logcat) para fines de depuración o seguimiento.
*   **Persistencia Básica:**
    *   Los datos ingresados en los campos y la información del último entrenamiento guardado persisten a través de cambios de configuración (como la rotación de pantalla) gracias al uso de `rememberSaveable`.

## ¿Cómo Funciona?

La aplicación utiliza Jetpack Compose para la interfaz de usuario. El usuario ingresa los detalles del entrenamiento en dos campos de texto. Al presionar "Guardar Entrenamiento", esta información se almacena temporalmente en el estado de la aplicación. Los campos de entrada se limpian. Un segundo botón, "Mostrar Información del Entrenamiento", se vuelve visible y, al presionarlo, muestra los detalles del entrenamiento guardado.

## Tecnologías Utilizadas

*   **Lenguaje:** Kotlin
*   **Interfaz de Usuario:** Jetpack Compose (UI declarativa moderna de Android)
*   **Componentes de Material Design 3:** Para una apariencia moderna y consistente.
*   **Gestión de Estado de Compose:** `mutableStateOf`, `rememberSaveable` para manejar el estado de la UI y la persistencia básica.
*   **Android SDK Básico:** `Activity`, `Context`, `Toast`, `Log`.

## Requisitos Previos (Para Desarrolladores)

*   Android Studio (versión recomendada Bumblebee o superior, para mejor soporte de Compose)
*   Configuración del SDK de Android

## Cómo Compilar y Ejecutar

1.  Clona este repositorio (o descarga el código fuente).
2.  Abre el proyecto en Android Studio.
3.  Permite que Gradle sincronice y descargue las dependencias necesarias.
4.  Ejecuta la aplicación en un emulador de Android o en un dispositivo físico.


## Posibles Mejoras Futuras

*   Integración con una base de datos local (SQLite, Room) para persistencia a largo plazo de múltiples entrenamientos.
*   Historial de entrenamientos.
*   Selección de deportes desde una lista predefinida.
*   Notificaciones para recordar sesiones de entrenamiento.
*   Sincronización con servicios en la nube.
*   Mejoras en la interfaz de usuario y la experiencia de usuario.
*   Localización a otros idiomas.


## Autor

*   César Londoño 

---
