package com.bytedance.apm.agent.instrumentation.io;

import java.util.ArrayList;
import java.util.List;
/* compiled from: StreamCompleteListenerManager.java */
/* loaded from: classes.dex */
class e {

    /* renamed from: a  reason: collision with root package name */
    private boolean f252a = false;

    /* renamed from: b  reason: collision with root package name */
    private ArrayList<d> f253b = new ArrayList<>();

    public boolean isComplete() {
        boolean z;
        synchronized (this) {
            z = this.f252a;
        }
        return z;
    }

    public void addStreamCompleteListener(d dVar) {
        synchronized (this.f253b) {
            this.f253b.add(dVar);
        }
    }

    public void removeStreamCompleteListener(d dVar) {
        synchronized (this.f253b) {
            this.f253b.remove(dVar);
        }
    }

    public void notifyStreamComplete(c cVar) {
        if (a()) {
            return;
        }
        for (d dVar : b()) {
            dVar.streamComplete(cVar);
        }
    }

    public void notifyStreamError(c cVar) {
        if (a()) {
            return;
        }
        for (d dVar : b()) {
            dVar.streamError(cVar);
        }
    }

    private boolean a() {
        boolean isComplete;
        synchronized (this) {
            isComplete = isComplete();
            if (!isComplete) {
                this.f252a = true;
            }
        }
        return isComplete;
    }

    private List<d> b() {
        ArrayList arrayList;
        synchronized (this.f253b) {
            arrayList = new ArrayList(this.f253b);
            this.f253b.clear();
        }
        return arrayList;
    }
}
