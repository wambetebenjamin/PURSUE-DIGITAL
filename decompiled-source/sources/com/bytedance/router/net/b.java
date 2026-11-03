package com.bytedance.router.net;
/* compiled from: NetResponse.java */
/* loaded from: classes2.dex */
public class b {
    public static final int SUCCESS = 200;
    public int errorCode = -1;
    public String content = "";

    public String toString() {
        return "[errorCode: " + this.errorCode + ", content:" + this.content + "]";
    }
}
