package com.bytedance.crash.util;

import com.bytedance.crash.NpthBus;
/* compiled from: SoUtils.java */
/* loaded from: classes2.dex */
public class ad {
    public static boolean loadLib(String str) {
        try {
            try {
                System.loadLibrary(str);
                return true;
            } catch (Throwable unused) {
                com.bytedance.librarian.b.loadLibraryForModule(str, NpthBus.getApplicationContext());
                return true;
            }
        } catch (Throwable unused2) {
            return false;
        }
    }
}
