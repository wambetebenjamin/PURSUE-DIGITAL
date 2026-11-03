package com.bytedance.geckox.policy.loop.model;

import com.bytedance.applog.forward.b;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes2.dex */
public class LoopInterval {
    @SerializedName(b.f2686c)
    private int interval;

    public int getInterval() {
        return this.interval;
    }

    public LoopInterval(int i2) {
        this.interval = i2;
    }

    /* loaded from: classes2.dex */
    public enum a {
        lv_1(1),
        lv_2(2),
        lv_3(3);
        

        /* renamed from: d  reason: collision with root package name */
        private int f9272d;

        a(int i2) {
            this.f9272d = i2;
        }

        public int a() {
            return this.f9272d;
        }
    }
}
