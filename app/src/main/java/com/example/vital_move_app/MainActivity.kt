package com.example.vital_move_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

//--- MAIN SCREEN ---
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // LINKING THE ELEMENTS OF THE VIEW (layouts) WITH THE LOGIC
        val openSaveActivity: Button = findViewById<Button>(R.id.btnSave)
        val openRealLifeActivity: Button = findViewById<Button>(R.id.btnRealTime)


    // CONNECTING THE BUTTONS WITH THEIR SCREENS
        //Save Activity Button
        openSaveActivity.setOnClickListener {
            val intent = Intent(this, SavingActivities::class.java)
            startActivity(intent)
        }

        //Real Time Activity Button
        openRealLifeActivity.setOnClickListener {
            val intent = Intent(this, RealTimeActivity::class.java)
            startActivity(intent)
        }

    }
}