package com.unity3d.player;

import android.util.Log;
/* renamed from: com.unity3d.player.z  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
abstract class AbstractC0445z {

    /* renamed from: a  reason: collision with root package name */
    protected static boolean f19079a = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public static void Log(int i2, String str) {
        if (f19079a) {
            return;
        }
        if (i2 == 6) {
            Log.e("Unity", str);
        }
        if (i2 == 5) {
            Log.w("Unity", str);
        }
    }
}
