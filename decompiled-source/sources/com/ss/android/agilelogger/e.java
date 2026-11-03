package com.ss.android.agilelogger;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LibLoader.java */
/* loaded from: classes9.dex */
public class e implements com.bytedance.android.alog.b {
    @Override // com.bytedance.android.alog.b
    public void loadLibrary(String str) {
        try {
            com.bytedance.librarian.b.loadLibraryForModule("alog", a.sConfig.getContext());
        } catch (Throwable unused) {
            System.loadLibrary("alog");
        }
    }
}
