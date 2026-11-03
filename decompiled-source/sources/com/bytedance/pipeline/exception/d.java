package com.bytedance.pipeline.exception;

import java.util.ArrayList;
import java.util.List;
/* compiled from: ParallelException.java */
/* loaded from: classes2.dex */
public class d extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private List<Throwable> f10012a;

    public d(List<Throwable> list) {
        this.f10012a = list;
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Throwable th : list) {
            a(th, arrayList);
        }
        setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[arrayList.size()]));
    }

    private void a(Throwable th, List<StackTraceElement> list) {
        if (th == null) {
            return;
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        for (int i2 = 0; i2 < length; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            if (i2 == 0) {
                String message = th.getMessage();
                StringBuilder append = new StringBuilder().append("\b\b\b\nCaused by:");
                if (message == null) {
                    message = "";
                }
                list.add(new StackTraceElement(append.append(message).append("\n\t").append(stackTraceElement.getClassName()).toString(), stackTraceElement.getMethodName(), stackTraceElement.getFileName(), stackTraceElement.getLineNumber()));
            } else {
                list.add(stackTraceElement);
            }
        }
        a(th.getCause(), list);
    }

    public List<Throwable> a() {
        return this.f10012a;
    }
}
