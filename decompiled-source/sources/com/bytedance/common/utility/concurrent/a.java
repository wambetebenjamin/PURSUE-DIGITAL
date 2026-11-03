package com.bytedance.common.utility.concurrent;

import android.os.AsyncTask;
import android.os.Build;
/* compiled from: AsyncTaskUtils.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    static final C0074a f5222a;

    /* compiled from: AsyncTaskUtils.java */
    /* renamed from: com.bytedance.common.utility.concurrent.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class C0074a {
        private C0074a() {
        }

        public <T> void executeAsyncTask(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            try {
                asyncTask.execute(tArr);
            } catch (Throwable unused) {
            }
        }
    }

    /* compiled from: AsyncTaskUtils.java */
    /* loaded from: classes2.dex */
    private static class b extends C0074a {
        private b() {
            super();
        }

        @Override // com.bytedance.common.utility.concurrent.a.C0074a
        public <T> void executeAsyncTask(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            try {
                asyncTask.executeOnExecutor(c.getNormalExecutor(), tArr);
            } catch (Throwable unused) {
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            f5222a = new b();
        } else {
            f5222a = new C0074a();
        }
    }

    public static <T> void executeAsyncTask(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        f5222a.executeAsyncTask(asyncTask, tArr);
    }
}
