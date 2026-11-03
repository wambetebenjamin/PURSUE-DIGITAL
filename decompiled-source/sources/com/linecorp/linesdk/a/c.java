package com.linecorp.linesdk.a;

import android.content.Context;
import java.util.concurrent.Executors;
/* loaded from: classes9.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static final com.linecorp.a.a.a.b f17268a = new com.linecorp.a.a.a.b("com.linecorp.linesdk.sharedpreference.encryptionsalt");

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f17269b = false;

    public static void a(Context context) {
        if (f17269b) {
            return;
        }
        f17269b = true;
        Executors.newSingleThreadExecutor().execute(new a(context.getApplicationContext()));
    }

    public static com.linecorp.a.a.a.b a() {
        return f17268a;
    }

    /* loaded from: classes9.dex */
    private static class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final Context f17270a;

        a(Context context) {
            this.f17270a = context;
        }

        @Override // java.lang.Runnable
        public final void run() {
            c.f17268a.a(this.f17270a);
        }
    }
}
