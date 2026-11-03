package com.ss.android.agilelogger;

import com.ss.android.agilelogger.utils.b;
/* compiled from: LogItem.java */
/* loaded from: classes9.dex */
public class f {
    public String className;
    public String lineNum;
    public b.a mFormatType;
    public int mLevel;
    public boolean mMainThread;
    public String mMsg;
    public Object mObj;
    public Object mObj2;
    public String mStackTraceInfo;
    public String mTag;
    public long mThreadId;
    public String mThreadInfo;
    public String methodName;
    public int oprationType = -1;

    @Deprecated
    public void recycle() {
    }

    public static f obtain() {
        return new f();
    }

    public static f obtain(int i2, String str, String str2, long j2, boolean z) {
        f obtain = obtain();
        obtain.mLevel = i2;
        obtain.mTag = str;
        obtain.mMsg = str2;
        obtain.mThreadId = j2;
        obtain.mMainThread = z;
        return obtain;
    }

    public static f obtain(int i2, String str, String str2, String str3, String str4) {
        f obtain = obtain();
        obtain.mLevel = i2;
        obtain.mTag = str;
        obtain.mMsg = str2;
        obtain.mThreadInfo = str3;
        obtain.mStackTraceInfo = str4;
        return obtain;
    }
}
