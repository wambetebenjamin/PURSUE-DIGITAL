package com.bytedance.ttnet.tnc;

import com.vk.api.sdk.exceptions.VKApiCodes;
import java.util.Map;
/* compiled from: TNCConfig.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public boolean f15454a = false;

    /* renamed from: b  reason: collision with root package name */
    public boolean f15455b = true;

    /* renamed from: c  reason: collision with root package name */
    public Map<String, Integer> f15456c = null;

    /* renamed from: d  reason: collision with root package name */
    public int f15457d = 10;

    /* renamed from: e  reason: collision with root package name */
    public int f15458e = 3;

    /* renamed from: f  reason: collision with root package name */
    public int f15459f = 3;

    /* renamed from: g  reason: collision with root package name */
    public int f15460g = 10;

    /* renamed from: h  reason: collision with root package name */
    public int f15461h = 3;

    /* renamed from: i  reason: collision with root package name */
    public int f15462i = 3;

    /* renamed from: j  reason: collision with root package name */
    public int f15463j = VKApiCodes.CODE_MSG_SEND_RECIPIENT_BLACKLISTED;
    public int k = 120;
    public String l = null;
    public int m = 0;
    public long n = 0;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" localEnable: ").append(this.f15454a);
        sb.append(" probeEnable: ").append(this.f15455b);
        StringBuilder append = sb.append(" hostFilter: ");
        Map<String, Integer> map = this.f15456c;
        append.append(map != null ? map.size() : 0);
        sb.append(" reqTo: ").append(this.f15457d).append("#").append(this.f15458e).append("#").append(this.f15459f);
        sb.append(" reqErr: ").append(this.f15460g).append("#").append(this.f15461h).append("#").append(this.f15462i);
        sb.append(" updateInterval: ").append(this.f15463j);
        sb.append(" updateRandom: ").append(this.k);
        sb.append(" httpBlack: ").append(this.l);
        return sb.toString();
    }
}
