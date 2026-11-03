package com.bytedancehttpdns.httpdns.util;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes8.dex */
public class Ipv6ConnectionManager {
    private static final String IPV4_PROBE_ADDRESS = "8.8.8.8";
    private static final String IPV6_PROBE_ADDRESS = "2001:4860:4860::8888";
    private static final String TAG = "veHttpDns";
    private static final AtomicBoolean mIsIpv6GloballyReachable = new AtomicBoolean(false);
    private static final AtomicBoolean mIsIpv4GloballyReachable = new AtomicBoolean(false);

    public static void detectNetworkReachable() {
        mIsIpv6GloballyReachable.set(isGloballyReachable(IPV6_PROBE_ADDRESS));
        mIsIpv4GloballyReachable.set(isGloballyReachable(IPV4_PROBE_ADDRESS));
    }

    private static boolean isGloballyReachable(String str) {
        boolean z = false;
        DatagramSocket datagramSocket = null;
        try {
            DatagramSocket datagramSocket2 = new DatagramSocket();
            try {
                datagramSocket2.connect(new InetSocketAddress(str, 53));
                InetAddress localAddress = datagramSocket2.getLocalAddress();
                if (localAddress instanceof Inet6Address) {
                    boolean z2 = !localAddress.isLinkLocalAddress();
                    if (!localAddress.getHostAddress().startsWith("2001:0000")) {
                        z = z2;
                    }
                } else {
                    z = true;
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

    public static AtomicBoolean getIpv6GloballyReachable() {
        return mIsIpv6GloballyReachable;
    }

    public static boolean ipv6Only() {
        return !mIsIpv4GloballyReachable.get() && mIsIpv6GloballyReachable.get();
    }
}
