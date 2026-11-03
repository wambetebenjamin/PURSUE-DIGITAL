package com.bytedance.sync;

import java.util.Observable;
/* compiled from: SDKInitObservable.java */
/* loaded from: classes3.dex */
public class k extends Observable {
    public void notifyInit() {
        setChanged();
        notifyObservers(true);
    }
}
