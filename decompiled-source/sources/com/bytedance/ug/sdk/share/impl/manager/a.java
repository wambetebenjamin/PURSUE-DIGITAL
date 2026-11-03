package com.bytedance.ug.sdk.share.impl.manager;

import java.util.ArrayList;
/* compiled from: AdditionalTokenDialogManager.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<com.bytedance.ug.sdk.share.api.ui.a> f15789a;

    /* compiled from: AdditionalTokenDialogManager.java */
    /* renamed from: com.bytedance.ug.sdk.share.impl.manager.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    private static class C0255a {

        /* renamed from: a  reason: collision with root package name */
        private static a f15790a = new a();

        private C0255a() {
        }
    }

    public static a getInstance() {
        return C0255a.f15790a;
    }

    public ArrayList<com.bytedance.ug.sdk.share.api.ui.a> getRecognizeTokenDialogList() {
        return this.f15789a;
    }

    public void register(ArrayList<com.bytedance.ug.sdk.share.api.ui.a> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        if (this.f15789a == null) {
            this.f15789a = new ArrayList<>();
        }
        this.f15789a.addAll(arrayList);
    }
}
