package com.ss.android.newmedia.redbadge.impl;

import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Arrays;
import java.util.List;
/* compiled from: SonyHomeBadger.java */
/* loaded from: classes9.dex */
public class o implements com.ss.android.newmedia.redbadge.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18431a = "com.sonyericsson.home.action.UPDATE_BADGE";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18432b = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18433c = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18434d = "com.sonyericsson.home.intent.extra.badge.MESSAGE";

    /* renamed from: e  reason: collision with root package name */
    private static final String f18435e = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE";

    /* renamed from: f  reason: collision with root package name */
    private static final String f18436f = "content://com.sonymobile.home.resourceprovider/badge";

    /* renamed from: g  reason: collision with root package name */
    private static final String f18437g = "badge_count";

    /* renamed from: h  reason: collision with root package name */
    private static final String f18438h = "package_name";

    /* renamed from: i  reason: collision with root package name */
    private static final String f18439i = "activity_name";

    /* renamed from: j  reason: collision with root package name */
    private static final String f18440j = "com.sonymobile.home.resourceprovider";
    private final Uri k = Uri.parse(f18436f);
    private AsyncQueryHandler l;

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        try {
            if (a(context)) {
                b(context, componentName, i2);
            } else {
                a(context, componentName, i2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.sonyericsson.home", "com.sonymobile.home");
    }

    private static void a(Context context, ComponentName componentName, int i2) {
        Intent intent = new Intent(f18431a);
        intent.putExtra(f18432b, componentName.getPackageName());
        intent.putExtra(f18433c, componentName.getClassName());
        intent.putExtra(f18434d, String.valueOf(i2));
        intent.putExtra(f18435e, i2 > 0);
        context.sendBroadcast(intent);
    }

    private void b(Context context, ComponentName componentName, int i2) {
        if (i2 < 0) {
            return;
        }
        if (this.l == null) {
            this.l = new a(context.getApplicationContext().getContentResolver()) { // from class: com.ss.android.newmedia.redbadge.impl.o.1
            };
        }
        a(i2, componentName.getPackageName(), componentName.getClassName());
    }

    private void a(int i2, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(f18437g, Integer.valueOf(i2));
        contentValues.put("package_name", str);
        contentValues.put(f18439i, str2);
        this.l.startInsert(0, null, this.k, contentValues);
    }

    private static boolean a(Context context) {
        return context.getPackageManager().resolveContentProvider(f18440j, 0) != null;
    }

    /* compiled from: SonyHomeBadger.java */
    /* loaded from: classes9.dex */
    private static class a extends AsyncQueryHandler {
        a(ContentResolver contentResolver) {
            super(contentResolver);
        }

        @Override // android.content.AsyncQueryHandler
        protected Handler createHandler(Looper looper) {
            return new C0332a(looper);
        }

        /* compiled from: SonyHomeBadger.java */
        /* renamed from: com.ss.android.newmedia.redbadge.impl.o$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        private class C0332a extends AsyncQueryHandler.WorkerHandler {
            C0332a(Looper looper) {
                super(a.this, looper);
            }

            @Override // android.content.AsyncQueryHandler.WorkerHandler, android.os.Handler
            public void handleMessage(Message message) {
                try {
                    super.handleMessage(message);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }
}
