package com.bytedance.sync.v2.net;
/* compiled from: NetTrace.java */
/* loaded from: classes3.dex */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    int f13838a = 0;

    /* renamed from: b  reason: collision with root package name */
    int f13839b = 0;
    public int mSendMsgType = 0;
    public int mIsConnect = 0;

    /* compiled from: NetTrace.java */
    /* loaded from: classes3.dex */
    public @interface a {
        public static final int UNKNOWN = 0;
        public static final int WITH_HTTPS = 2;
        public static final int WITH_SOCKET = 1;
    }

    /* compiled from: NetTrace.java */
    /* loaded from: classes3.dex */
    public @interface b {
        public static final int SEND_FAILED = 2;
        public static final int SEND_FAILED_NET_NOT_AVAILABLE = 3;
        public static final int SEND_SUCCESS = 1;
        public static final int UNKNOWN = 0;
    }

    /* compiled from: NetTrace.java */
    /* loaded from: classes3.dex */
    public @interface c {
        public static final int HTTP_FIRST_BACKGROUND = 4;
        public static final int HTTP_FIRST_FOREGROUND = 3;
        public static final int UNKNOWN = 0;
        public static final int WS_FIRST_CONNECTED = 1;
        public static final int WS_FIRST_DISCONNECTED = 2;
    }

    /* compiled from: NetTrace.java */
    /* loaded from: classes3.dex */
    public interface d {
        int getCurrentStrategy();
    }

    public abstract void send();
}
