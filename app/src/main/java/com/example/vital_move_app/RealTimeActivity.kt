package com.example.vital_move_app

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.math.abs
import kotlin.math.sqrt

// --- REAL TIME ACTIVITY LOGGING SCREEN ---
class RealTimeActivity : AppCompatActivity(), SensorEventListener {

    // UI/Layouts Components
    private lateinit var tvMovement: TextView
    private lateinit var mainLayout: LinearLayout
    private lateinit var btnFinish: Button

    // Sensor Components
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    //Acceleration parameters
    private var lastAcceleration = 0f
    private var currentAcceleration = 0f
    private var shakeIntensity = 0f


    // Movement parameters
    private val WAITING = 1.0f   // Below this = Walking
    private val WALKING = 8.0f   // Between Walking and this is Jogging
    // ------------------------->   Above JOGGING = Fly!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.realtime_session_activity)

        // Initializing UI/Layouts components
        tvMovement = findViewById(R.id.MovementLevel)
        mainLayout = findViewById(R.id.mainLayout)
        btnFinish = findViewById(R.id.btnFinish)

        // Initializing sensor manager and accelerometer
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


        // Check if accelerometer is available
        if (accelerometer == null) {
            tvMovement.text = "Accelerometer not available on this device"
        }

        //Initializing the acceleration values using Earth's gravity (to prevents exaggerated results)
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH


        // FINISH SESSION BUTTON
        btnFinish.setOnClickListener {
            finish() // Close the Activity and return to Main Screen
        }
    }


    override fun onSensorChanged(event: SensorEvent) {

        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {

            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = sqrt(x * x + y * y + z * z)

            lastAcceleration = currentAcceleration
            currentAcceleration = acceleration

            //Delta = difference between the current and previous reading
            val delta = currentAcceleration - lastAcceleration
            shakeIntensity = abs(delta)

            // Update UI/Layout based on movement intensity
            updateMovementState(delta)
        }
    }
            private fun updateMovementState(acceleration: Float) {
                when {
                    acceleration < WAITING -> {
                        // Low intensity movement
                        tvMovement.text = "Waiting.."
                        mainLayout.setBackgroundColor(
                            ContextCompat.getColor(this, R.color.waiting)
                        )
                    }
                    acceleration < WALKING -> {
                        // Medium intensity movement
                        tvMovement.text = "Walking"
                        mainLayout.setBackgroundColor(
                            ContextCompat.getColor(this, R.color.walking)
                        )
                    }
                    else -> {
                        // High intensity movement
                        tvMovement.text = "Fly, you fools!"
                        mainLayout.setBackgroundColor(
                            ContextCompat.getColor(this, R.color.flying)
                        )
                    }
                }
            }

            // REGISTER SENSOR Listener WHEN Activity BECOMES VISIBLE
    override fun onResume() {
        super.onResume()
        accelerometer?.also { sensor ->
            sensorManager.registerListener(
                this,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )

        }
    }

    // STOP LISTENING TO SENSOR (to save battery) WHEN THIS ACTIVITY IS NOT VISIBLE
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    // NOT USED BUT REQUIRED BY SensorEventListener INTERFACE
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

}