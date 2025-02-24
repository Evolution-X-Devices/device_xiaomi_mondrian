/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2020 The LineageOS Project
 * Copyright (C) 2023 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Display;
import android.content.SharedPreferences;
import android.os.SystemProperties;
import androidx.preference.PreferenceManager;
import android.os.IBinder;
import android.view.Display.HdrCapabilities;

import org.lineageos.settings.display.ColorService;
import org.lineageos.settings.dirac.DiracUtils;
import org.lineageos.settings.doze.AodBrightnessService;
import org.lineageos.settings.doze.DozeUtils;
import org.lineageos.settings.doze.PocketService;
import org.lineageos.settings.display.ColorService;
import org.lineageos.settings.feas.FEASUtils;
import org.lineageos.settings.thermal.ThermalUtils;
import org.lineageos.settings.refreshrate.RefreshUtils;

public class BootCompletedReceiver extends BroadcastReceiver {

    private static final boolean DEBUG = false;
    private static final String TAG = "XiaomiParts-BCR";

    @Override
    public void onReceive(final Context context, Intent intent) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (DEBUG) Log.d(TAG, "Received boot completed intent");
        ThermalUtils.startService(context);

        Log.i(TAG, "Boot completed");

        // Dirac
        DiracUtils.initialize(context);

        // Doze
        DozeUtils.checkDozeService(context);

        // Pocket
        PocketService.startService(context);

        // DisplayFeature
        ColorService.startService(context);

        // AOD
        AodBrightnessService.startService(context);

        // Per app refresh rate
        RefreshUtils.startService(context);

        // Override HDR types
        final DisplayManager displayManager = context.getSystemService(DisplayManager.class);
        displayManager.overrideHdrTypes(Display.DEFAULT_DISPLAY, new int[]{
                HdrCapabilities.HDR_TYPE_DOLBY_VISION, HdrCapabilities.HDR_TYPE_HDR10,
                HdrCapabilities.HDR_TYPE_HLG, HdrCapabilities.HDR_TYPE_HDR10_PLUS});

        // FEAS
        final FEASUtils mFEASUtils = new FEASUtils(context);
        mFEASUtils.initialize();
    }
}
