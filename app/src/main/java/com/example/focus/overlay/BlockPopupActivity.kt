package com.example.focus.overlay

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.focus.R

class BlockPopupActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_block_popup)

        // MATCHING IDs FROM UPDATED XML
        val titleText: TextView = findViewById(R.id.level_ping)
        val imageView: ImageView = findViewById(R.id.popup_lottie)
        val okButton: Button = findViewById(R.id.btn_ok)

        // Title
        titleText.text = "This App is Blocked"

        // OK Button closes popup
        okButton.setOnClickListener {
            finish()
        }
    }
}
