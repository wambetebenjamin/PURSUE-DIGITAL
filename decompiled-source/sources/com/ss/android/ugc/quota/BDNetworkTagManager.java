package com.ss.android.ugc.quota;

import android.app.Application;
import android.util.Log;
import android.util.Pair;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
public class BDNetworkTagManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18726a = "BDNetworkTagManager";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18727b = "x-tt-request-tag";

    /* renamed from: c  reason: collision with root package name */
    private static volatile BDNetworkTagManager f18728c;

    /* renamed from: d  reason: collision with root package name */
    private Application f18729d;

    /* renamed from: e  reason: collision with root package name */
    private d f18730e;

    /* renamed from: f  reason: collision with root package name */
    private com.ss.android.ugc.quota.launch.b f18731f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f18732g;

    /* renamed from: h  reason: collision with root package name */
    private AtomicBoolean f18733h = new AtomicBoolean(false);

    /* renamed from: i  reason: collision with root package name */
    private AtomicBoolean f18734i = new AtomicBoolean(false);

    /* renamed from: j  reason: collision with root package name */
    private int f18735j = com.ss.android.ugc.quota.tag.a.UNSPECIFIED;

    private BDNetworkTagManager() {
    }

    public static BDNetworkTagManager getInstance() {
        if (f18728c == null) {
            synchronized (BDNetworkTagManager.class) {
                if (f18728c == null) {
                    f18728c = new BDNetworkTagManager();
                }
            }
        }
        return f18728c;
    }

    public void init(Application application, d dVar) {
        if (this.f18734i.get()) {
            Log.d(f18726a, "Already inited");
        } else if (application == null || dVar == null) {
            throw new IllegalArgumentException("Context or depend is NULL");
        } else {
            this.f18729d = application;
            this.f18730e = dVar;
            this.f18733h.set(dVar.enable());
            this.f18731f = dVar.launchMonitor() == null ? new com.ss.android.ugc.quota.launch.a(application) : dVar.launchMonitor();
            this.f18732g = dVar.isNewInstall();
            this.f18734i.compareAndSet(false, true);
        }
    }

    public void enable(boolean z) {
        this.f18733h.set(z);
    }

    public void updateLaunchType(int i2) {
        if (a()) {
            this.f18735j = i2;
            d dVar = this.f18730e;
            if (dVar != null) {
                dVar.onLaunchTypeUpdate(i2);
            }
        }
    }

    public Pair<String, String> buildBDNetworkTag(c cVar) {
        if (a()) {
            if (cVar == null) {
                throw new IllegalArgumentException("ContextProvider is NULL");
            }
            int currentLaunchType = this.f18731f.currentLaunchType();
            if (this.f18735j != currentLaunchType) {
                updateLaunchType(currentLaunchType);
            }
            return new Pair<>(f18727b, "t=" + cVar.triggerType() + ";n=" + (cVar.markAsNewUser() ? 1 : 0));
        }
        return null;
    }

    public int currentLaunchType() {
        return !a() ? com.ss.android.ugc.quota.tag.a.UNSPECIFIED : this.f18735j;
    }

    public boolean isFirstInstallLaunch() {
        if (a()) {
            return this.f18732g;
        }
        return false;
    }

    private boolean a() {
        return this.f18734i.get() && this.f18733h.get();
    }
}
