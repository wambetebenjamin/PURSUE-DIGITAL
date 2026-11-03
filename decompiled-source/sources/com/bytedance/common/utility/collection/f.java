package com.bytedance.common.utility.collection;

import android.database.DataSetObserver;
import java.util.Iterator;
/* compiled from: WeakDataSetObservable.java */
/* loaded from: classes2.dex */
public class f<T extends DataSetObserver> {

    /* renamed from: a  reason: collision with root package name */
    protected final e<T> f5217a = new e<>();

    public void registerObserver(T t) {
        if (t == null) {
            throw new IllegalArgumentException("The observer is null.");
        }
        synchronized (this.f5217a) {
            this.f5217a.add(t);
        }
    }

    public void unregisterObserver(T t) {
        if (t == null) {
            throw new IllegalArgumentException("The observer is null.");
        }
        synchronized (this.f5217a) {
            this.f5217a.remove(t);
        }
    }

    public void unregisterAll() {
        synchronized (this.f5217a) {
            this.f5217a.clear();
        }
    }

    public void notifyChanged() {
        synchronized (this.f5217a) {
            Iterator<T> it = this.f5217a.iterator();
            while (it.hasNext()) {
                it.next().onChanged();
            }
        }
    }

    public void notifyInvalidated() {
        synchronized (this.f5217a) {
            Iterator<T> it = this.f5217a.iterator();
            while (it.hasNext()) {
                it.next().onInvalidated();
            }
        }
    }
}
