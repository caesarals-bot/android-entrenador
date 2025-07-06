package com.ideasprojects.cesar_londono_20250706

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
// import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ideasprojects.cesar_londono_20250706.ui.theme.Cesar_londono_20250706Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("EntrenadorVirtual", "La aplicación 'Entrenador Virtual' ha sido iniciada.")
        setContent {
            Cesar_londono_20250706Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrainingScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingScreen() {
    val context = LocalContext.current

    var currentSportNameInput by rememberSaveable { mutableStateOf("") }
    var currentTrainingTimeInput by rememberSaveable { mutableStateOf("") }

    var savedSportName by rememberSaveable { mutableStateOf<String?>(null) }
    var savedTrainingTime by rememberSaveable { mutableStateOf<String?>(null) }
    var isTrainingDataSaved by rememberSaveable { mutableStateOf(false) }

    // Nuevo estado para controlar la visibilidad de la información guardada en la UI
    var showSavedInfoInUI by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Configura tu Entrenamiento",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = currentSportNameInput,
            onValueChange = { currentSportNameInput = it },
            label = { Text("Deporte a entrenar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = currentTrainingTimeInput,
            onValueChange = { newValue ->
                currentTrainingTimeInput = newValue.filter { it.isDigit() }
            },
            label = { Text("Tiempo (en minutos)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val sportToSave = currentSportNameInput.trim()
                val timeToSave = currentTrainingTimeInput.trim()

                if (sportToSave.isEmpty()) {
                    Toast.makeText(context, "Por favor, ingresa un deporte.", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val timeInt = timeToSave.toIntOrNull()
                if (timeToSave.isEmpty() || timeInt == null || timeInt <= 0) {
                    Toast.makeText(context, "Por favor, ingresa un tiempo válido en minutos.", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                savedSportName = sportToSave
                savedTrainingTime = timeToSave
                isTrainingDataSaved = true
                showSavedInfoInUI = false // Ocultar info anterior si se guarda una nueva

                Toast.makeText(context, "Entrenamiento guardado temporalmente.", Toast.LENGTH_SHORT).show()

                // LIMPIAR FORMULARIO DESPUÉS DE GUARDAR
                currentSportNameInput = ""
                currentTrainingTimeInput = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Entrenamiento")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isTrainingDataSaved) {
            Button(
                onClick = {
                    val message = "Entrenamiento solicitado:\nDeporte: $savedSportName\nTiempo: $savedTrainingTime minutos"
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    Log.i("EntrenamientoInfo", "Deporte: $savedSportName, Tiempo: $savedTrainingTime minutos")
                    showSavedInfoInUI = true // Marcar para mostrar la info en la UI
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Mostrar Información del Entrenamiento")
            }
        }

        // Mostrar la información guardada directamente en la UI si está marcada para mostrarse
        if (isTrainingDataSaved && showSavedInfoInUI) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Último Entrenamiento Guardado:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Deporte: ${savedSportName ?: "No disponible"}")
            Text("Tiempo: ${savedTrainingTime ?: "No disponible"} minutos")
        }
    }
}

@Preview(showBackground = true, name = "Training Screen Initial")
@Composable
fun TrainingScreenInitialPreview() {
    Cesar_londono_20250706Theme {
        TrainingScreen()
    }
}

@Preview(showBackground = true, name = "Training Screen Data Saved and Shown")
@Composable
fun TrainingScreenDataSavedAndShownPreview() {
    Cesar_londono_20250706Theme {
        val context = LocalContext.current
        var currentSportNameInput by rememberSaveable { mutableStateOf("") } // Limpio, como si se acabara de guardar
        var currentTrainingTimeInput by rememberSaveable { mutableStateOf("") } // Limpio

        var savedSportName by rememberSaveable { mutableStateOf<String?>("Natación") }
        var savedTrainingTime by rememberSaveable { mutableStateOf<String?>("45") }
        var isTrainingDataSaved by rememberSaveable { mutableStateOf(true) }
        var showSavedInfoInUI by rememberSaveable { mutableStateOf(true) } // Para mostrar en la preview

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Configura tu Entrenamiento",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            OutlinedTextField(value = currentSportNameInput, onValueChange = {}, label = { Text("Deporte a entrenar") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = currentTrainingTimeInput, onValueChange = {}, label = { Text("Tiempo (en minutos)") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { /* ... */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Guardar Entrenamiento")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (isTrainingDataSaved) {
                Button(onClick = { /* ... */ }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                    Text("Mostrar Información del Entrenamiento")
                }
            }
            if (isTrainingDataSaved && showSavedInfoInUI) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Último Entrenamiento Guardado:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Deporte: ${savedSportName ?: "No disponible"}")
                Text("Tiempo: ${savedTrainingTime ?: "No disponible"} minutos")
            }
        }
    }
}