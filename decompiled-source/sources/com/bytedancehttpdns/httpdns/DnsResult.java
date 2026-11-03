package com.bytedancehttpdns.httpdns;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class DnsResult implements Serializable {
    public String host = "";
    public List<String> ipv4List = new ArrayList();
    public List<String> ipv6List = new ArrayList();
    public Source source = Source.UNKNOWN;
    public long ttl = 0;
    public long rtt = 0;
    public String cip = "";
    public List<DnsTaskInfo> taskInfoList = new ArrayList();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("host : ").append(this.host).append(", ").append("ipv4 list : ").append(this.ipv4List).append(", ").append("ipv6 list : ").append(this.ipv6List).append(", ").append("source : ").append(this.source).append(", ").append("ttl : ").append(this.ttl).append(", ").append("rtt : ").append(this.rtt).append(", ").append("cip : ").append(this.cip);
        return sb.toString();
    }
}
