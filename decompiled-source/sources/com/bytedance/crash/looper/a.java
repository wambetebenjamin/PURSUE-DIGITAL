package com.bytedance.crash.looper;

import java.io.File;
/* compiled from: LooperMessageConfig.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    static final String f6808a = "current_message";

    /* renamed from: b  reason: collision with root package name */
    static final String f6809b = "history_message";

    /* renamed from: c  reason: collision with root package name */
    static final String f6810c = "pending_messages";

    /* renamed from: d  reason: collision with root package name */
    static final String f6811d = "history_details";

    /* renamed from: e  reason: collision with root package name */
    static final String f6812e = "npth_message_";

    /* renamed from: f  reason: collision with root package name */
    static final String f6813f = "block_stack";

    /* renamed from: g  reason: collision with root package name */
    static final int f6814g = 1;

    /* renamed from: h  reason: collision with root package name */
    static final int f6815h = 8;

    /* renamed from: i  reason: collision with root package name */
    static final int f6816i = 9;

    /* renamed from: j  reason: collision with root package name */
    private static final String f6817j = "history.json";
    private static final String k = "current.json";
    private static final String l = "pending.json";
    private static final String m = "block_stack.json";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File a(File file) {
        return new File(file, k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File b(File file) {
        return new File(file, f6817j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File c(File file) {
        return new File(file, l);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File d(File file) {
        return new File(file, m);
    }
}
