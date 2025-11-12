package com.example.focus.accessibility

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.util.Log
import com.example.focus.overlay.BlockPopupActivity

class FocusAccessibilityService : AccessibilityService() {
    private val TAG = "FocusAccessibility"
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        val pkg = event.packageName?.toString() ?: return

        val prefs = getSharedPreferences("focus_prefs", MODE_PRIVATE)
        val active = prefs.getBoolean("focus_active", false)
        if (!active) return

        val blockInstagram = prefs.getBoolean("block_instagram", false)
        val blockInstagramReels = prefs.getBoolean("block_instagram_reels", false)
        val blockYouTube = prefs.getBoolean("block_youtube", false)
        val blockYouTubeShorts = prefs.getBoolean("block_youtube_shorts", false)

        if (pkg == "com.instagram.android" && blockInstagram && blockInstagramReels) {
            val root = rootInActiveWindow ?: return
            if (findReelText(root)) {
                Log.d(TAG, "Instagram Reels detected")
                performGlobalAction(GLOBAL_ACTION_HOME)
                val i = Intent(this, BlockPopupActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
            }
        }

        if (pkg == "com.google.android.youtube" && blockYouTube && blockYouTubeShorts) {
            val root = rootInActiveWindow ?: return
            if (findShortsText(root)) {
                Log.d(TAG, "YouTube Shorts detected")
                performGlobalAction(GLOBAL_ACTION_HOME)
                val i = Intent(this, BlockPopupActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
            }
        }
    }

    private fun findReelText(node: AccessibilityNodeInfo?): Boolean {
        if (node == null) return false
        try {
            val txt = node.text?.toString()?.lowercase()
            if (txt != null && txt.contains("reel")) return true
            val cd = node.contentDescription?.toString()?.lowercase()
            if (cd != null && cd.contains("reel")) return true
            val rid = node.viewIdResourceName
            if (rid != null && rid.lowercase().contains("reel")) return true
            for (i in 0 until node.childCount) {
                val c = node.getChild(i)
                if (findReelText(c)) return true
            }
        } catch (t: Throwable) {}
        return false
    }

    private fun findShortsText(node: AccessibilityNodeInfo?): Boolean {
        if (node == null) return false
        try {
            val txt = node.text?.toString()?.lowercase()
            if (txt != null && (txt.contains("shorts") || txt.contains("short"))) return true
            val cd = node.contentDescription?.toString()?.lowercase()
            if (cd != null && (cd.contains("shorts") || cd.contains("short"))) return true
            val rid = node.viewIdResourceName
            if (rid != null && rid.lowercase().contains("shorts")) return true
            for (i in 0 until node.childCount) {
                val c = node.getChild(i)
                if (findShortsText(c)) return true
            }
        } catch (t: Throwable) {}
        return false
    }

    override fun onInterrupt() {}
}
