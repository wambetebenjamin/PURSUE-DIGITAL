package com.bytedance.platform.godzilla;

import android.app.Activity;
import android.app.Application;
import com.bytedance.platform.godzilla.common.d;
import com.bytedance.platform.godzilla.common.i;
import com.bytedance.platform.godzilla.common.k;
import com.bytedance.platform.godzilla.common.m;
import java.lang.ref.WeakReference;
import java.util.HashMap;
/* compiled from: GodzillaCore.java */
/* loaded from: classes2.dex */
public enum b {
    INSTANCE;
    

    /* renamed from: a  reason: collision with root package name */
    private static final String f11029a = "UncaughtExceptionPlugin";

    /* renamed from: b  reason: collision with root package name */
    private static HashMap<Integer, WeakReference<Activity>> f11030b = new HashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private static final int f11031c = 0;

    /* renamed from: d  reason: collision with root package name */
    private static final int f11032d = 1;

    /* renamed from: e  reason: collision with root package name */
    private static final int f11033e = 2;

    /* renamed from: f  reason: collision with root package name */
    private static final int f11034f = 3;

    /* renamed from: g  reason: collision with root package name */
    private WeakReference<Activity> f11035g = null;

    /* renamed from: h  reason: collision with root package name */
    private WeakReference<Activity> f11036h = null;

    /* renamed from: i  reason: collision with root package name */
    private WeakReference<Activity> f11037i = null;

    /* renamed from: j  reason: collision with root package name */
    private WeakReference<Activity> f11038j = null;
    private d k = null;

    b() {
    }

    public void init(Application application, i iVar, k.a aVar) {
        if (iVar != null) {
            k.setLog(iVar);
        }
        if (aVar != null) {
            k.setLogLevel(aVar);
        }
    }

    private void a() {
        if (this.k == null) {
            d dVar = new d();
            this.k = dVar;
            dVar.register();
        }
        k.i(f11029a, "init mConsumeExceptionHandler:" + this.k);
    }

    public void addUncaughtExceptionConsumer(m mVar) {
        a();
        k.i(f11029a, "add consumer:" + mVar);
        this.k.addExceptionConsumer(mVar);
    }

    public void removeUncaughtExceptionConsumer(m mVar) {
        k.i(f11029a, "remove consumer:" + mVar);
        this.k.removeExceptionConsumer(mVar);
    }

    public void destroy() {
        d dVar = this.k;
        if (dVar != null) {
            dVar.removeAllExceptionConsumer();
        }
    }
}
