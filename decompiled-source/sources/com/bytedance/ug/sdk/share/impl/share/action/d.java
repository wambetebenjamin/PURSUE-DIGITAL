package com.bytedance.ug.sdk.share.impl.share.action;

import com.bytedance.ug.sdk.share.api.entity.h;
/* compiled from: VideoShareAction.java */
/* loaded from: classes8.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static volatile d f15937a;

    private d() {
    }

    public static d getInstance() {
        if (f15937a == null) {
            synchronized (d.class) {
                if (f15937a == null) {
                    f15937a = new d();
                }
            }
        }
        return f15937a;
    }

    public boolean shareTo(h hVar) {
        if (hVar == null) {
            return false;
        }
        return new com.bytedance.ug.sdk.share.impl.helper.d().shareVideo(hVar);
    }
}
