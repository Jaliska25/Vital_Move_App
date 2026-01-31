package com.example.vital_move_app.model

import java.sql.Timestamp

// DATA MODEL REPRESENTING A SINGLE ACTIVITY SESSION
data class ActivitySession (

    val activity: String,
    val duration: Int,
    val timestamp: Long,
    val activityType: String,

    )