package com.bytedance.common.jato.memory.LeakRepair.debug;

import android.util.Log;
import com.bytedance.common.jato.memory.LeakRepair.d;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/* compiled from: LeakReporter.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4849a = "actName";

    /* renamed from: b  reason: collision with root package name */
    public static final String f4850b = "viewCount";

    /* renamed from: c  reason: collision with root package name */
    public static final String f4851c = "reclaimtime";

    /* renamed from: e  reason: collision with root package name */
    private static final boolean f4852e = true;

    /* renamed from: d  reason: collision with root package name */
    private InterfaceC0063b f4853d;

    /* renamed from: f  reason: collision with root package name */
    private List<a> f4854f = new LinkedList();

    /* compiled from: LeakReporter.java */
    /* renamed from: com.bytedance.common.jato.memory.LeakRepair.debug.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0063b {
        void a(HashMap<String, String> hashMap);
    }

    public void a(InterfaceC0063b interfaceC0063b) {
        this.f4853d = interfaceC0063b;
    }

    public void a(String str, int i2) {
        if (this.f4853d == null) {
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(f4849a, str);
        hashMap.put(f4850b, String.valueOf(i2));
        this.f4853d.a(hashMap);
    }

    public void a(a aVar) {
        this.f4854f.add(aVar);
        Log.e(d.f4833a, "reclaim renderNode: " + aVar.toString());
    }

    /* compiled from: LeakReporter.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f4855a = "";

        /* renamed from: b  reason: collision with root package name */
        public String f4856b = "";

        /* renamed from: c  reason: collision with root package name */
        public long f4857c = 0;

        /* renamed from: d  reason: collision with root package name */
        public int f4858d = -1;

        /* renamed from: e  reason: collision with root package name */
        public int f4859e = -1;

        /* renamed from: f  reason: collision with root package name */
        public int f4860f = -1;

        /* renamed from: g  reason: collision with root package name */
        public int f4861g = -1;

        /* renamed from: h  reason: collision with root package name */
        public int f4862h = 0;

        public String toString() {
            return String.format("View:%s:%s (%s, %s)x(%s, %s), ActName:%s, RenderNode:%s", this.f4856b, Integer.valueOf(this.f4862h), Integer.valueOf(this.f4858d), Integer.valueOf(this.f4859e), Integer.valueOf(this.f4860f), Integer.valueOf(this.f4861g), this.f4855a, Long.valueOf(this.f4857c));
        }
    }
}
