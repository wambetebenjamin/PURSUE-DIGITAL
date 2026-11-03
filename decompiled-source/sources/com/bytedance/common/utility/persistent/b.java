package com.bytedance.common.utility.persistent;

import android.content.SharedPreferences;
import android.os.Build;
/* compiled from: SharedPrefsEditorCompat.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    static final InterfaceC0077b f5341a;

    /* compiled from: SharedPrefsEditorCompat.java */
    /* renamed from: com.bytedance.common.utility.persistent.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    interface InterfaceC0077b {
        void apply(SharedPreferences.Editor editor);
    }

    /* compiled from: SharedPrefsEditorCompat.java */
    /* loaded from: classes2.dex */
    static class a implements InterfaceC0077b {
        a() {
        }

        @Override // com.bytedance.common.utility.persistent.b.InterfaceC0077b
        public void apply(SharedPreferences.Editor editor) {
            editor.commit();
        }
    }

    /* compiled from: SharedPrefsEditorCompat.java */
    /* loaded from: classes2.dex */
    static class c implements InterfaceC0077b {
        c() {
        }

        @Override // com.bytedance.common.utility.persistent.b.InterfaceC0077b
        public void apply(SharedPreferences.Editor editor) {
            editor.apply();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 9) {
            f5341a = new c();
        } else {
            f5341a = new a();
        }
    }

    public static void apply(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        f5341a.apply(editor);
    }
}
