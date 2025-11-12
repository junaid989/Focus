package com.example.focus.overlay

import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.focus.R

class BlockPopupActivity : AppCompatActivity() {
    private var player: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        setContentView(R.layout.activity_block_popup)

        try {
            player = MediaPlayer.create(this, R.raw.level_ping)
            player?.start()
        } catch (e: Exception) {}

        val lottie = findViewById<LottieAnimationView>(R.id.popup_lottie)
        lottie.setAnimation(R.raw.popup_sparkle)
        lottie.playAnimation()

        findViewById<android.widget.Button>(R.id.btn_ok).setOnClickListener { finish() }
    }
    override fun onDestroy() { super.onDestroy(); player?.release(); player = null }
}
