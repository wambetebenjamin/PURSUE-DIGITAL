package com.ss.android.agilelogger.formatter.thread;
/* compiled from: DefaultThreadFormatter.java */
/* loaded from: classes9.dex */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private final String f18007a = "Thread: ";

    @Override // com.ss.android.agilelogger.formatter.b
    public String format(Thread thread) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Thread: ");
        stringBuffer.append(thread.getName());
        return stringBuffer.toString();
    }
}
