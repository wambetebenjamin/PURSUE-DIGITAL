package com.bytedance.ttgame.module.netdiagnose;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.common.utility.l;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.netdiagnose.api.INetDiagnoseService;
import com.bytedance.ttgame.module.netdiagnose.api.data.IpAddressResult;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: NetDiagnoseService.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/bytedance/ttgame/module/netdiagnose/NetDiagnoseService;", "Lcom/bytedance/ttgame/module/netdiagnose/api/INetDiagnoseService;", "()V", "getIPAddress", "Lcom/bytedance/ttgame/module/netdiagnose/api/data/IpAddressResult;", "context", "Landroid/content/Context;", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class NetDiagnoseService implements INetDiagnoseService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.module.netdiagnose.api.INetDiagnoseService
    public IpAddressResult getIPAddress(Context context) {
        this.moduleApiMonitor.onApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.module.netdiagnose.api.INetDiagnoseService", "com.bytedance.ttgame.module.netdiagnose.NetDiagnoseService", "getIPAddress", new String[]{"android.content.Context"}, "com.bytedance.ttgame.module.netdiagnose.api.data.IpAddressResult");
        IpAddressResult ipAddressResult = new IpAddressResult(null, 1, null);
        if (context != null && l.isNetworkAvailable(context)) {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    if (nextElement.isUp()) {
                        List<InterfaceAddress> interfaceAddresses = nextElement.getInterfaceAddresses();
                        Intrinsics.checkNotNullExpressionValue(interfaceAddresses, "interfaceAddresses");
                        for (InterfaceAddress interfaceAddress : interfaceAddresses) {
                            InetAddress address = interfaceAddress.getAddress();
                            if (!address.isLoopbackAddress() && (address instanceof Inet4Address)) {
                                String hostAddress = address.getHostAddress();
                                Intrinsics.checkNotNullExpressionValue(hostAddress, "inetAddress.getHostAddress()");
                                ipAddressResult.setIpv4Address(hostAddress);
                            }
                        }
                        for (InterfaceAddress interfaceAddress2 : interfaceAddresses) {
                            InetAddress address2 = interfaceAddress2.getAddress();
                            if (!address2.isLoopbackAddress() && (address2 instanceof Inet6Address)) {
                                String hostAddress2 = address2.getHostAddress();
                                Intrinsics.checkNotNullExpressionValue(hostAddress2, "inetAddress.getHostAddress()");
                                ipAddressResult.setIpv6Address(hostAddress2);
                            }
                        }
                        if (!TextUtils.isEmpty(ipAddressResult.getIpv4Address()) && !TextUtils.isEmpty(ipAddressResult.getIpv6Address())) {
                            break;
                        }
                    }
                }
            } catch (SocketException unused) {
            }
            return ipAddressResult;
        }
        return ipAddressResult;
    }
}
