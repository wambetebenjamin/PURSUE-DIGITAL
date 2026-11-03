package com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util;

import com.bytedance.common.utility.Logger;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: Ipv6ConnectionManager.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8068a = "d";

    /* renamed from: b  reason: collision with root package name */
    private static String f8069b = "2001:4860:4860::8888";

    /* renamed from: c  reason: collision with root package name */
    private static AtomicBoolean f8070c = new AtomicBoolean(false);

    public static void a() {
        if (Logger.debug()) {
            Logger.d(f8068a, "detectIpv6Reachable");
        }
        f8070c.set(c());
    }

    private static boolean c() {
        boolean z = false;
        DatagramSocket datagramSocket = null;
        try {
            DatagramSocket datagramSocket2 = new DatagramSocket();
            try {
                datagramSocket2.connect(new InetSocketAddress(f8069b, 53));
                InetAddress localAddress = datagramSocket2.getLocalAddress();
                if (localAddress instanceof Inet6Address) {
                    boolean z2 = !localAddress.isLinkLocalAddress();
                    if (!localAddress.getHostAddress().startsWith("2001:0000")) {
                        z = z2;
                    }
                }
                datagramSocket2.close();
            } catch (IOException unused) {
                datagramSocket = datagramSocket2;
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                return z;
            } catch (Throwable th) {
                th = th;
                datagramSocket = datagramSocket2;
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        return z;
    }

    public static AtomicBoolean b() {
        return f8070c;
    }
}
