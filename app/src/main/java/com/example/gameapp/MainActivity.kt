package com.example.gameapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

interface Screens {
    fun getLayout(): Int
    fun getListeners(): Map<Int, Screens>
}

object MainScreen: Screens {
    override fun getLayout(): Int {
        return R.layout.activity_main
    }
    override fun getListeners(): Map<Int, Screens> {
        val result = mapOf(R.id.buttonAchievements to AchievementScreen,
            R.id.buttonStore to StoreScreen,
            R.id.buttonSettings to SettingsScreen)
        return result
    }
}

object AchievementScreen: Screens {
    override fun getLayout(): Int {
        return R.layout.achievement_screen
    }
    override fun getListeners(): Map<Int, Screens> {
        val result = mapOf(R.id.buttonAchievements to MainScreen,
            R.id.buttonStore to StoreScreen,
            R.id.buttonSettings to SettingsScreen)
        return result
    }
}

object StoreScreen: Screens {
    override fun getLayout(): Int {
        return R.layout.store_screen
    }
    override fun getListeners(): Map<Int, Screens> {
        val result = mapOf(R.id.buttonAchievements to AchievementScreen,
            R.id.buttonStore to MainScreen,
            R.id.buttonSettings to SettingsScreen)
        return result
    }
}

object SettingsScreen: Screens {
    override fun getLayout(): Int {
        return R.layout.settings_screen
    }
    override fun getListeners(): Map<Int, Screens> {
        val result = mapOf(R.id.buttonAchievements to AchievementScreen,
            R.id.buttonStore to StoreScreen,
            R.id.buttonSettings to MainScreen)
        return result
    }
}

class MainActivity : AppCompatActivity() {

    var state: Screens = MainScreen

    fun switchState(newState: Screens) {
        this.state = newState
        setContentView(state.getLayout())
        for(btn in state.getListeners()){
            findViewById<Button>(btn.key).setOnClickListener() {
                switchState(btn.value)
            }
            println("${btn.key} - ${btn.value}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        switchState(MainScreen)
        /*setContentView(R.layout.activity_main)
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
        */
    }
}