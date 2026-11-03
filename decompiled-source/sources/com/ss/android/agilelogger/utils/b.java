package com.ss.android.agilelogger.utils;

import android.content.Intent;
import android.os.Bundle;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: FormatUtils.java */
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static ConcurrentHashMap<a, com.ss.android.agilelogger.formatter.b> f18008a;

    /* compiled from: FormatUtils.java */
    /* loaded from: classes9.dex */
    public enum a {
        MSG,
        JSON,
        BUNDLE,
        INTENT,
        THROWABLE,
        BORDER,
        STACKTRACE,
        THREAD,
        STACKTRACE_STR
    }

    static {
        ConcurrentHashMap<a, com.ss.android.agilelogger.formatter.b> concurrentHashMap = new ConcurrentHashMap<>();
        f18008a = concurrentHashMap;
        concurrentHashMap.put(a.MSG, new com.ss.android.agilelogger.formatter.message.a());
        f18008a.put(a.JSON, new com.ss.android.agilelogger.formatter.message.json.a());
        f18008a.put(a.BUNDLE, new com.ss.android.agilelogger.formatter.message.object.a());
        f18008a.put(a.INTENT, new com.ss.android.agilelogger.formatter.message.object.b());
        f18008a.put(a.BORDER, new com.ss.android.agilelogger.formatter.border.b());
        f18008a.put(a.STACKTRACE, new com.ss.android.agilelogger.formatter.stacktrace.a());
        f18008a.put(a.THREAD, new com.ss.android.agilelogger.formatter.thread.a());
        f18008a.put(a.THROWABLE, new com.ss.android.agilelogger.formatter.message.throwable.a());
    }

    public static String format(a aVar, String str) {
        com.ss.android.agilelogger.formatter.b bVar = f18008a.get(aVar);
        if (bVar != null) {
            if (aVar == a.BORDER) {
                return bVar.format(new String[]{str});
            }
            return bVar.format(str);
        }
        return str;
    }

    public static String format(a aVar, StackTraceElement[] stackTraceElementArr) {
        return f18008a.get(aVar).format(stackTraceElementArr);
    }

    public static String format(a aVar, Bundle bundle) {
        return ((com.ss.android.agilelogger.formatter.message.object.a) f18008a.get(aVar)).format(bundle);
    }

    public static String format(a aVar, Intent intent) {
        return ((com.ss.android.agilelogger.formatter.message.object.b) f18008a.get(aVar)).format(intent);
    }

    public static String format(a aVar, Throwable th) {
        return f18008a.get(aVar).format(th);
    }

    public static String format(a aVar, Thread thread) {
        return f18008a.get(aVar).format(thread);
    }
}
