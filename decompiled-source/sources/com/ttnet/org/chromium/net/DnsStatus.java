package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
import java.net.InetAddress;
import java.util.List;
@JNINamespace("net::android")
/* loaded from: classes9.dex */
public class DnsStatus {
    private final List<InetAddress> mDnsServers;
    private final boolean mPrivateDnsActive;
    private final String mPrivateDnsServerName;
    private final String mSearchDomains;

    public DnsStatus(List<InetAddress> list, boolean z, String str, String str2) {
        this.mDnsServers = list;
        this.mPrivateDnsActive = z;
        this.mPrivateDnsServerName = str == null ? "" : str;
        this.mSearchDomains = str2 == null ? "" : str2;
    }

    public byte[][] getDnsServers() {
        byte[][] bArr = new byte[this.mDnsServers.size()];
        for (int i2 = 0; i2 < this.mDnsServers.size(); i2++) {
            bArr[i2] = this.mDnsServers.get(i2).getAddress();
        }
        return bArr;
    }

    public boolean getPrivateDnsActive() {
        return this.mPrivateDnsActive;
    }

    public String getPrivateDnsServerName() {
        return this.mPrivateDnsServerName;
    }

    public String getSearchDomains() {
        return this.mSearchDomains;
    }
}
