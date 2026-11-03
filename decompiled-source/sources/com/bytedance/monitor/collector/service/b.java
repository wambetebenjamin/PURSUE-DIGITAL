package com.bytedance.monitor.collector.service;

import android.os.Message;
import java.util.HashSet;
import java.util.Set;
/* compiled from: MessageHandleUtils.java */
/* loaded from: classes2.dex */
public class b {
    public static final int BIND_SERVICE = 121;
    public static final int CREATE_SERVICE = 114;
    public static final int DUMP_SERVICE = 123;
    public static final int SERVICE_ARGS = 115;
    public static final int STOP_SERVICE = 116;
    public static final int UNBIND_SERVICE = 122;

    /* renamed from: a  reason: collision with root package name */
    private static Set<Integer> f9648a;

    static {
        HashSet hashSet = new HashSet();
        f9648a = hashSet;
        hashSet.add(114);
        f9648a.add(115);
        f9648a.add(116);
        f9648a.add(121);
        f9648a.add(122);
        f9648a.add(123);
    }

    public static boolean isServiceMessage(Message message) {
        return f9648a.contains(Integer.valueOf(message.what));
    }
}
