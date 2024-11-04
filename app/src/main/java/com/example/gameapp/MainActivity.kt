package com.example.gameapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonAchievements = findViewById<Button>(R.id.buttonAchievements)
        val buttonStore = findViewById<Button>(R.id.buttonStore)
        val buttonSettings = findViewById<Button>(R.id.buttonSettings)

        buttonAchievements.setOnClickListener() {
            setContentView(R.layout.achievement_screen)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.achievements)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            Toast.makeText(this, R.string.hint_achievements, Toast.LENGTH_LONG).show()
        }

        buttonStore.setOnClickListener() {
            Toast.makeText(this, R.string.hint_store, Toast.LENGTH_LONG).show()
            findViewById<FrameLayout>(R.id.game_choose_menu).visibility = View.INVISIBLE
        }

        buttonSettings.setOnClickListener() {
            Toast.makeText(this, R.string.hint_settings, Toast.LENGTH_LONG).show()
            findViewById<FrameLayout>(R.id.game_choose_menu).visibility = View.VISIBLE
        }
    }
}