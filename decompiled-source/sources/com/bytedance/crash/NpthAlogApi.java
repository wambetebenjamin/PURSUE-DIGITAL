package com.bytedance.crash;

import java.util.List;
/* loaded from: classes2.dex */
public class NpthAlogApi {

    /* renamed from: a  reason: collision with root package name */
    private static Runnable f6082a;

    /* renamed from: b  reason: collision with root package name */
    private static AlogGetFilesInterface f6083b;

    /* renamed from: c  reason: collision with root package name */
    private static AlogInitInterface f6084c;

    /* loaded from: classes2.dex */
    interface AlogGetFilesInterface {
        List<String> getFiles(long j2, String str);
    }

    /* loaded from: classes2.dex */
    interface AlogInitInterface {
        boolean isInited();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Runnable runnable) {
        f6082a = runnable;
    }

    public static boolean sAlogFlushInit() {
        return f6082a != null;
    }

    public static void flushAlogSync() {
        Runnable runnable = f6082a;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(AlogGetFilesInterface alogGetFilesInterface) {
        f6083b = alogGetFilesInterface;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(AlogInitInterface alogInitInterface) {
        f6084c = alogInitInterface;
    }

    public static boolean getAlogFilesInit() {
        return f6083b != null;
    }

    public static boolean isAlogInit() {
        AlogInitInterface alogInitInterface = f6084c;
        return alogInitInterface != null && alogInitInterface.isInited();
    }

    public static List<String> getAlogFiles(long j2, String str) {
        AlogGetFilesInterface alogGetFilesInterface = f6083b;
        if (alogGetFilesInterface == null) {
            return null;
        }
        return alogGetFilesInterface.getFiles(j2, str);
    }
}
