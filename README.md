# Vital Move App 📱

**Vital Move** is a native Android application developed as a practical exercise for the **Multimedia Programming and Mobile Devices** module.

This project demonstrates the implementation of key Android components, including manual data persistence and real-time hardware sensor usage, following the strict guidelines of the course assignment.

## ✨ Features

### 1. Manual Activity Logging 
* Users can manually log their workouts by specifying the activity name, duration, and type of activity.
* **Data Persistence:** Saved sessions are displayed in a list using a custom `RecyclerView` with an optimized `ListAdapter`.
* **Input Validation:** Ensures data integrity before saving.
  

### 2. Real-Time Sensor Tracking 
* Utilizes the device's **Accelerometer** to detect movement intensity in real-time.
* **Dynamic UI:** The background color and status text change based on the G-force detected (Waiting, Walking, Running).
  

## 🛠️ Tech Stack
* **Language:** Kotlin
* **Architecture:** MVC (Model-View-Controller)
* **Components:**
    * `SensorManager` & `SensorEventListener` (Hardware Sensors)
    * `RecyclerView` & `ViewBinding` (UI)
    * `Intents` (Navigation)
      

## 🚧 Project Status
I am aware that there is a lot of room for improvement in this application. However, this project has been invaluable for **solidifying my fundamental knowledge of Kotlin and Android Studio**. It has served as a solid foundation for building more complex applications in the future.

---
* There's a small tribute to The Lord of the Rings 🧙🏻‍♂️ since it's the 25th anniversary of one of my favorite movies 🤭.
