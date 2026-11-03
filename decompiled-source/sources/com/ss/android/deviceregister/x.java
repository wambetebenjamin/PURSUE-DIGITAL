package com.ss.android.deviceregister;

import android.net.wifi.WifiInfo;
import android.telephony.SubscriptionInfo;
import android.util.Pair;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
/* compiled from: SensitiveApiCallback.java */
/* loaded from: classes9.dex */
public interface x {
    String a();

    String a(WifiInfo wifiInfo);

    String a(SubscriptionInfo subscriptionInfo);

    byte[] a(NetworkInterface networkInterface);

    String b();

    String b(WifiInfo wifiInfo);

    String c();

    String d();

    String e();

    String f();

    String g();

    String h();

    WifiInfo i();

    Enumeration<NetworkInterface> j();

    List<SubscriptionInfo> k();

    String l();

    String m();

    Pair<String, Boolean> n();

    String o();
}
