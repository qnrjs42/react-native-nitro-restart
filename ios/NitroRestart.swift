//
//  NitroRestart.swift
//  NitroRestart
//
//  Created by qnrjs42 on 8/21/2025.
//
import UIKit
import Foundation
import React

class NitroRestart: HybridNitroRestartSpec {
  func restartApp() {
    let loadBundle = {
      RCTTriggerReloadCommandListeners("react-native-restart: Restart")
    }
    
    if Thread.isMainThread {
      loadBundle()
    } else {
      DispatchQueue.main.sync {
        loadBundle()
      }
    }
  }

  func exitApp() {
    DispatchQueue.main.async {
      UIApplication.shared.perform(#selector(NSXPCConnection.suspend))
      DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
        exit(0)
      }
    }
  }
}