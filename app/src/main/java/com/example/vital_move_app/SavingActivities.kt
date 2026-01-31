package com.example.vital_move_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vital_move_app.model.ActivitySession
import kotlin.String

// MANUAL ACTIVITY LOGGING SCREEN
class SavingActivities : AppCompatActivity() {


    // SESSIONS LIST
    private val sessions = mutableListOf<ActivitySession>()

    private lateinit var adapter: ElementsSavedAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.save_activity)

        // LINKING THE ELEMENTS OF THE VIEW (layouts) WITH THE LOGIC
        val etActivityName = findViewById<EditText>(R.id.etTextActivityName)
        val etDurationMinutes = findViewById<EditText>(R.id.etTextDuration)
        val etTypeActivity = findViewById<EditText>(R.id.etTextType)
        val btnSave = findViewById<Button>(R.id.btnSaveButton)
        val exitButton = findViewById<Button>(R.id.btnExit)
        val list = findViewById<RecyclerView>(R.id.List)


        //LINKING THE ADAPTER AND RECYCLERVIEW
        adapter = ElementsSavedAdapter(sessions)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        //SAVING THE INPUTS WHEN PRESSING BUTTON SAVE
        btnSave.setOnClickListener {
            // Activity name
            val name = etActivityName.text.toString().trim()
            if (name.isEmpty()) {
                etActivityName.error = "Enter an activity, please"
                return@setOnClickListener
            } else null

            // Duration of the Activity in Minutes
            val duration = etDurationMinutes.text.toString().trim()
            if (duration.isEmpty()) {
                etDurationMinutes.error = "Enter a number, please"
                return@setOnClickListener
            } else null

            //Converting String to Int
            val durationInt = duration.toInt()


            //Activity Type
            val type = etTypeActivity.text.toString().trim()
            if (type.isEmpty()) {
                etActivityName.error = "Enter type of activity, please"
                return@setOnClickListener
            } else null

            // Creating a session
            val session = ActivitySession(
                activity = name, // Asumiendo que tu modelo usa 'activity' como nombre
                duration = durationInt,
                timestamp = System.currentTimeMillis(), // Cambiado timestamp por date (según tu modelo anterior)
                activityType = type
            )

            // Update UI and Data
            sessions.add(0, session)
            adapter.notifyItemInserted(0)

            // Scroll to see the new item
            list.scrollToPosition(0)


            //Cleaning fields after saving
            etActivityName.text.clear()
            etDurationMinutes.text.clear()
            etTypeActivity.text.clear()
        }

        // BUTTON EXIT AND COME BACK TO MAIN SCREEN
        exitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}