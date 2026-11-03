package com.bytedance.librarian;

import android.util.Log;
import com.bytedance.librarian.c;
/* compiled from: LibrarianMonitor.java */
/* loaded from: classes2.dex */
public class d {
    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str) {
        Log.d(c.a.TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(String str) {
        Log.w(c.a.TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, Throwable th) {
        Log.w(c.a.TAG, str, th);
    }

    protected void c(String str) {
        Log.e(c.a.TAG, str);
    }

    protected void b(String str, Throwable th) {
        Log.e(c.a.TAG, str, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(String str) {
        System.loadLibrary(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(String str) {
        System.load(str);
    }
}
