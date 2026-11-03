package com.unity3d.player;

import android.view.inputmethod.InputMethodSubtype;
/* renamed from: com.unity3d.player.y  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
abstract class AbstractC0443y {
    public static String a(InputMethodSubtype inputMethodSubtype) {
        return PlatformSupport.NOUGAT_SUPPORT ? inputMethodSubtype.getLanguageTag() : inputMethodSubtype.getLocale();
    }
}
