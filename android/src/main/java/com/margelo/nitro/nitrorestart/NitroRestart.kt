package com.margelo.nitro.restart

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.facebook.proguard.annotations.DoNotStrip
import com.facebook.react.bridge.ReactContext
import com.margelo.nitro.NitroModules

@DoNotStrip
class NitroRestart : HybridNitroRestartSpec() {
  private val applicationContext = NitroModules.applicationContext
  
  private fun getCurrentActivity(): Activity? {
    return try {
      val reactContext = applicationContext as? ReactContext
      reactContext?.currentActivity
    } catch (e: Exception) {
      Log.w("NitroRestart", "Unable to get current activity: ${e.message}")
      null
    }
  }

  override fun restartApp() {
    Handler(Looper.getMainLooper()).post {
      try {
        val currentActivity = getCurrentActivity()
        if (currentActivity != null) {
          val intent = currentActivity.packageManager
            .getLaunchIntentForPackage(currentActivity.packageName)
          
          if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            currentActivity.startActivity(intent)
            currentActivity.finish()
            Runtime.getRuntime().exit(0)
          }
        }
      } catch (e: Exception) {
        Log.e("NitroRestart", "Failed to restart app: ${e.message}")
      }
    }
  }

  override fun exitApp() {
    try {
      val currentActivity = getCurrentActivity()
      currentActivity?.finishAffinity()
      android.os.Process.killProcess(android.os.Process.myPid())
    } catch (e: Exception) {
      Log.e("NitroRestart", "Failed to exit app: ${e.message}")
    }
  }
}