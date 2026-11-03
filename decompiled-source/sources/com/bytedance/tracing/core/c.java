package com.bytedance.tracing.core;

import com.bytedance.apm.util.m;
import java.util.LinkedList;
import java.util.Map;
/* compiled from: TraceWrapper.java */
@Deprecated
/* loaded from: classes3.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14041a = "test_trace";

    /* renamed from: b  reason: collision with root package name */
    private static final ThreadLocal<LinkedList<com.bytedance.tracing.core.a>> f14042b = new ThreadLocal<>();

    /* renamed from: c  reason: collision with root package name */
    private static final ThreadLocal<LinkedList<String>> f14043c = new ThreadLocal<>();

    /* compiled from: TraceWrapper.java */
    /* loaded from: classes3.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        com.bytedance.tracing.core.a f14044a;

        /* renamed from: b  reason: collision with root package name */
        String f14045b;

        a(com.bytedance.tracing.core.a aVar, String str) {
            this.f14044a = aVar;
            this.f14045b = str;
        }
    }

    public static void startTrace(String str) {
        ThreadLocal<LinkedList<String>> threadLocal = f14043c;
        LinkedList<String> linkedList = threadLocal.get();
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            threadLocal.set(linkedList);
        }
        linkedList.push(str);
    }

    public static void endTrace() {
        LinkedList<String> linkedList = f14043c.get();
        if (m.isEmpty(linkedList)) {
            return;
        }
        linkedList.pop();
    }

    public static a getFrozenContext() {
        LinkedList<com.bytedance.tracing.core.a> linkedList = f14042b.get();
        LinkedList<String> linkedList2 = f14043c.get();
        if (m.isEmpty(linkedList) || m.isEmpty(linkedList2)) {
            return null;
        }
        return new a(linkedList.peek(), linkedList2.peek());
    }

    public static void applyTraceContext(a aVar) {
        ThreadLocal<LinkedList<com.bytedance.tracing.core.a>> threadLocal = f14042b;
        LinkedList<com.bytedance.tracing.core.a> linkedList = threadLocal.get();
        ThreadLocal<LinkedList<String>> threadLocal2 = f14043c;
        LinkedList<String> linkedList2 = threadLocal2.get();
        if (!m.isEmpty(linkedList)) {
            linkedList.clear();
        } else if (linkedList == null) {
            linkedList = new LinkedList<>();
            threadLocal.set(linkedList);
        }
        if (!m.isEmpty(linkedList2)) {
            linkedList2.clear();
        } else if (linkedList2 == null) {
            linkedList2 = new LinkedList<>();
            threadLocal2.set(linkedList2);
        }
        linkedList.push(aVar.f14044a);
        linkedList2.push(aVar.f14045b);
    }

    public static void clearTraceContext() {
        LinkedList<com.bytedance.tracing.core.a> linkedList = f14042b.get();
        LinkedList<String> linkedList2 = f14043c.get();
        if (!m.isEmpty(linkedList)) {
            linkedList.clear();
        }
        if (m.isEmpty(linkedList2)) {
            return;
        }
        linkedList2.clear();
    }

    public static void beginSectionCurrentThread(String str) {
        ThreadLocal<LinkedList<com.bytedance.tracing.core.a>> threadLocal = f14042b;
        LinkedList<com.bytedance.tracing.core.a> linkedList = threadLocal.get();
        LinkedList<String> linkedList2 = f14043c.get();
        if (linkedList == null) {
            if (linkedList2 == null) {
                return;
            }
            linkedList = new LinkedList<>();
            threadLocal.set(linkedList);
        }
        if (linkedList.isEmpty()) {
            linkedList.push(new com.bytedance.tracing.core.a(linkedList2 != null ? linkedList2.peek() : null, str).start());
            return;
        }
        com.bytedance.tracing.core.a peek = linkedList.peek();
        if (peek.getFinishTs() > 0) {
            linkedList.pop();
            com.bytedance.tracing.core.a peek2 = linkedList.peek();
            if (peek2 == null) {
                linkedList.push(peek.makeChild(str).start());
                return;
            } else {
                linkedList.push(peek2.makeChildAndRef(str, peek.getSpanId()).start());
                return;
            }
        }
        linkedList.push(peek.makeChild(str).start());
    }

    public static void endSectionCurrentThread() {
        com.bytedance.tracing.core.a peek;
        LinkedList<com.bytedance.tracing.core.a> linkedList = f14042b.get();
        if (m.isEmpty(linkedList) || (peek = linkedList.peek()) == null) {
            return;
        }
        if (peek.getFinishTs() > 0) {
            peek = linkedList.pop();
        }
        peek.getFinishTs();
        peek.finish();
    }

    public static void addTraceTag(String str, String str2) {
        com.bytedance.tracing.core.a peek;
        LinkedList<com.bytedance.tracing.core.a> linkedList = f14042b.get();
        if (m.isEmpty(linkedList) || (peek = linkedList.peek()) == null) {
            return;
        }
        peek.addTag(str, str2);
    }

    public static void addErrTag(String str) {
        addTraceTag("error", str);
    }

    public static void addTraceLog(String str, Map<String, String> map) {
        com.bytedance.tracing.core.a peek;
        LinkedList<com.bytedance.tracing.core.a> linkedList = f14042b.get();
        if (m.isEmpty(linkedList) || (peek = linkedList.peek()) == null) {
            return;
        }
        peek.addLog(str, map);
    }

    public static void addTraceLog(String str) {
        com.bytedance.tracing.core.a peek;
        LinkedList<com.bytedance.tracing.core.a> linkedList = f14042b.get();
        if (m.isEmpty(linkedList) || (peek = linkedList.peek()) == null) {
            return;
        }
        peek.addLog(str);
    }

    private static String a(String str, int i2) {
        StringBuilder sb = new StringBuilder(str.length() * i2);
        while (i2 > 0) {
            sb.append(str);
            i2--;
        }
        return sb.toString();
    }
}
