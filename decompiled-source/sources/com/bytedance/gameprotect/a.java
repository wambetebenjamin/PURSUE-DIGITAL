package com.bytedance.gameprotect;
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static InterfaceC0125a f8852a;

    /* renamed from: b  reason: collision with root package name */
    private static String f8853b;
    private static String mFingerPrint;

    /* renamed from: com.bytedance.gameprotect.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0125a {
        void a(String str);

        void onFingerPrintComplete(String str);
    }

    public static synchronized void a(InterfaceC0125a interfaceC0125a) {
        synchronized (a.class) {
            f8852a = interfaceC0125a;
            String str = f8853b;
            if (str != null) {
                interfaceC0125a.a(str);
            }
            String str2 = mFingerPrint;
            if (str2 != null) {
                interfaceC0125a.onFingerPrintComplete(str2);
            }
        }
    }

    public static synchronized void a(String str) {
        synchronized (a.class) {
            f8853b = str;
            InterfaceC0125a interfaceC0125a = f8852a;
            if (interfaceC0125a != null) {
                interfaceC0125a.a(str);
            }
        }
    }

    public static synchronized void setFingerPrint(String str) {
        synchronized (a.class) {
            mFingerPrint = str;
            InterfaceC0125a interfaceC0125a = f8852a;
            if (interfaceC0125a != null) {
                interfaceC0125a.onFingerPrintComplete(str);
            }
        }
    }
}
