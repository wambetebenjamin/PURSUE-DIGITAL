package com.bytedance.platform.godzilla.common;
/* compiled from: CrashPortrait.java */
/* loaded from: classes2.dex */
public class f {
    public String appVersion;
    public String clazzName;
    public String detailMessage;
    public String methodName;
    public int osVersion;
    public String processName;
    public String threadName;
    public String throwableClassName;
    public int updateVersion;

    public String toString() {
        return "CrashPortrait{processName='" + this.processName + "', clazzName='" + this.clazzName + "', methodName='" + this.methodName + "', threadName='" + this.threadName + "', appVersion='" + this.appVersion + "', updateVersion=" + this.updateVersion + ", detailMessage='" + this.detailMessage + "', throwableClassName='" + this.throwableClassName + "', osVersion=" + this.osVersion + '}';
    }
}
