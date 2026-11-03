package com.bytedancehttpdns.httpdns;

import com.bytedancehttpdns.httpdns.util.Ipv6ConnectionManager;
import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public class Ipv6DetectCall implements Callable<Void> {
    @Override // java.util.concurrent.Callable
    public Void call() {
        Ipv6ConnectionManager.detectNetworkReachable();
        return null;
    }
}
