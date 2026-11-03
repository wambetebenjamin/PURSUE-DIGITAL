package com.bytedance.common.wschannel.channel;

import android.content.Context;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.l;
/* compiled from: WsChannelDependAdapter.java */
/* loaded from: classes2.dex */
public class c implements IWsChannelDepend {

    /* renamed from: a  reason: collision with root package name */
    public static c f5402a = new c();

    @Override // com.bytedance.common.wschannel.channel.IWsChannelDepend
    public void setAdapter(IWsChannelDepend iWsChannelDepend) {
    }

    private c() {
    }

    public static c a() {
        return f5402a;
    }

    @Override // com.bytedance.common.wschannel.channel.IWsChannelDepend
    public boolean loggerDebug() {
        return Logger.debug();
    }

    @Override // com.bytedance.common.wschannel.channel.IWsChannelDepend
    public void loggerD(String str, String str2) {
        Logger.d(str, str2);
    }

    @Override // com.bytedance.common.wschannel.channel.IWsChannelDepend
    public void loadLibrary(Context context, String str) {
        com.bytedance.common.utility.tools.a.loadLibrary(context, str);
    }

    @Override // com.bytedance.common.wschannel.channel.IWsChannelDepend
    public int getNetworkType(Context context) {
        return l.getNetworkType(context).getValue();
    }
}
