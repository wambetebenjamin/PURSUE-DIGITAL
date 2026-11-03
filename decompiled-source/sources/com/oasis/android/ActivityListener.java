package com.oasis.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
/* loaded from: classes9.dex */
public interface ActivityListener {
    default void onActivityResult(int i2, int i3, Intent intent) {
    }

    default void onBackPressed() {
    }

    default void onConfigurationChanged(Configuration configuration) {
    }

    default void onCreate(Activity activity, Bundle bundle) {
    }

    default void onCreate(Bundle bundle) {
    }

    default void onDestroy() {
    }

    default boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    default void onNewIntent(Intent intent) {
    }

    default void onPause() {
    }

    default void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
    }

    default void onRestart() {
    }

    default void onRestoreInstanceState(Bundle bundle) {
    }

    default void onResume() {
    }

    default void onSaveInstanceState(Bundle bundle) {
    }

    default void onStart() {
    }

    default void onStop() {
    }
}
