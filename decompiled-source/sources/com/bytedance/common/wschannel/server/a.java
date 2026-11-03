package com.bytedance.common.wschannel.server;

import com.bytedance.common.wschannel.channel.IWsChannelClient;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ChannelKeeper.java */
/* loaded from: classes2.dex */
final class a {

    /* renamed from: a  reason: collision with root package name */
    Map<Integer, com.bytedance.common.wschannel.app.b> f5898a = new ConcurrentHashMap();

    /* renamed from: b  reason: collision with root package name */
    Map<Integer, IWsChannelClient> f5899b = new ConcurrentHashMap();

    /* renamed from: c  reason: collision with root package name */
    Map<Integer, com.bytedance.common.wschannel.model.f> f5900c = new ConcurrentHashMap();
}
