package com.example.native_code;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.plugin.common.MethodChannel;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "com.test/test";

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);

    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
        .setMethodCallHandler((call, result) -> {
          if (call.method.equals("powerManage")) {
            boolean thermalLevel = getThermalLevel();

            // if (thermalLevel != -1) {
             String myMessage =  Boolean.toString(thermalLevel);
              result.success(myMessage);
            // } else {
            //   result.error("UNAVAILABLE", "Battery level not available.", null);
            // }
        

          }

        });
  }

  private boolean getThermalLevel() {
    boolean thermalLevel = false;
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
      thermalLevel = powerManager.isDeviceIdleMode();

    } 
  
    return thermalLevel;

  }
}
