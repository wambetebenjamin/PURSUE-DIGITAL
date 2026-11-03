package com.bytedance.common.wschannel.event;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: MessageAckEvent.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private a f5632a = a.Default;

    /* renamed from: b  reason: collision with root package name */
    private final String f5633b;

    /* renamed from: c  reason: collision with root package name */
    private final int f5634c;

    /* renamed from: d  reason: collision with root package name */
    private final int f5635d;

    /* renamed from: e  reason: collision with root package name */
    private final int f5636e;

    /* renamed from: f  reason: collision with root package name */
    private JSONObject f5637f;

    /* compiled from: MessageAckEvent.java */
    /* loaded from: classes2.dex */
    public enum a {
        Default(0),
        TimeOut(1),
        Failed(2),
        Success(3);
        

        /* renamed from: e  reason: collision with root package name */
        final int f5643e;

        a(int i2) {
            this.f5643e = i2;
        }

        public int a() {
            return this.f5643e;
        }

        public static a a(int i2) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        return Success;
                    }
                    return Default;
                }
                return Failed;
            }
            return TimeOut;
        }
    }

    public d(String str, int i2, int i3, int i4, String str2) {
        this.f5633b = str;
        this.f5634c = i2;
        this.f5635d = i3;
        this.f5636e = i4;
        try {
            this.f5637f = new JSONObject(str2);
        } catch (JSONException unused) {
        }
    }

    public String a() {
        return this.f5633b;
    }

    public a b() {
        return this.f5632a;
    }

    public void a(a aVar) {
        this.f5632a = aVar;
    }

    public int c() {
        return this.f5634c;
    }

    public int d() {
        return this.f5635d;
    }

    public int e() {
        return this.f5636e;
    }

    public JSONObject f() {
        return this.f5637f;
    }

    public String toString() {
        return "UniqueId:" + this.f5633b + ", ChannelId:" + this.f5634c + ", methodId:" + this.f5636e + ", state:" + this.f5632a + ", logInfo:" + this.f5637f.toString();
    }
}
