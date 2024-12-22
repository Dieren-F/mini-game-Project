package com.example.gameapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.os.Bundle
import android.view.SurfaceView
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.time.Duration.Companion.hours

interface Screens {
    fun getLayout(): Int
    //fun getLayout(context: Context, size: Point): SurfaceView?
    fun getListeners(): Map<Int, Screens>?
    fun getTypeView(): Boolean
}

object MainScreen: Screens {
    override fun getLayout(): Int {
        return R.layout.activity_main
    }
    override fun getListeners(): Map<Int, Screens> {
        val result = mapOf(R.id.buttonAchievements to AchievementScreen,
            R.id.buttonStore to StoreScreen,
            R.id.buttonSettings to SettingsScreen,
            R.id.buttonGameOne to FallingBlocksScreen,
            R.id.buttonGameTwo to SpaceInvadersScreen)
        return result
    }
    override fun getTypeView(): Boolean {
        return true
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
    override fun getTypeView(): Boolean {
        return true
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
    override fun getTypeView(): Boolean {
        return true
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
    override fun getTypeView(): Boolean {
        return true
    }
}

object FallingBlocksScreen: Screens {
    override fun getLayout(): Int {
        return R.layout.falling_blocks
    }
    override fun getListeners(): Map<Int, Screens> {
        val result = mapOf(R.id.buttonRestart to FallingBlocksScreen,
            R.id.buttonMainMenu to MainScreen)
        return result
    }
    override fun getTypeView(): Boolean {
        return true
    }
}

object SpaceInvadersScreen: Screens {
    override fun getLayout(): Int {
        return R.layout.falling_blocks
    }
    override fun getListeners(): Map<Int, Screens>? {
        val result = null
        return result
    }
    override fun getTypeView(): Boolean {
        return false
    }
}

class MainActivity : AppCompatActivity() {

    var state: Screens = MainScreen
    private var gameView: KotlinInvadersView? = null
    //enableEdgeToEdge()
    fun switchState(newState: Screens) {
        this.state = newState
        if (this.state.getTypeView()) {
            setContentView(state.getLayout())
            state.getListeners()?.let {
                for (btn in it) {
                    findViewById<Button>(btn.key).setOnClickListener() {
                        switchState(btn.value)
                    }
                    println("${btn.key} - ${btn.value}, this is a println message")
                }
            }
        }
        else {
            // Get a Display object to access screen details
            val display = windowManager.defaultDisplay
            // Load the resolution into a Point object
            val size = Point()
            display.getSize(size)

            // Initialize gameView and set it as the view
            gameView = KotlinInvadersView(this, size)
            setContentView(gameView)
            //onPause()
            onResume()
            println("This is the space for invaders")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
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

    // This method executes when the player starts the game
    override fun onResume() {
        super.onResume()

        // Tell the gameView resume method to execute
        gameView?.resume()
    }

    // This method executes when the player quits the game
    override fun onPause() {
        super.onPause()

        // Tell the gameView pause method to execute
        gameView?.pause()
    }
}