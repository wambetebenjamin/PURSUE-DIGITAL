package com.bytedance.ttgame.core.net;

import com.bytedance.ttgame.main.internal.net.IRetrofit;
import com.bytedance.ttgame.main.internal.net.TTHttpApi;
import java.util.Map;
/* loaded from: classes3.dex */
public class Proxy__TTHttpApiImpl implements TTHttpApi {
    private TTHttpApiImpl proxy = new TTHttpApiImpl();

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public String doPost(IRetrofit iRetrofit, String str, Map map, TTHttpApi.TTHttpBody tTHttpBody) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "doPost", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "java.lang.String", "java.util.Map", "com.bytedance.ttgame.main.internal.net.TTHttpApi$TTHttpBody"}, "java.lang.String");
        return this.proxy.doPost(iRetrofit, str, map, tTHttpBody);
    }

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public String doPost(IRetrofit iRetrofit, String str, Map map, TTHttpApi.TTHttpBody tTHttpBody, boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "doPost", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "java.lang.String", "java.util.Map", "com.bytedance.ttgame.main.internal.net.TTHttpApi$TTHttpBody", "boolean"}, "java.lang.String");
        return this.proxy.doPost(iRetrofit, str, map, tTHttpBody, z);
    }

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public String doPost(IRetrofit iRetrofit, String str, Map map, Map map2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "doPost", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "java.lang.String", "java.util.Map", "java.util.Map"}, "java.lang.String");
        return this.proxy.doPost(iRetrofit, str, map, map2);
    }

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public String get(IRetrofit iRetrofit, boolean z, String str, Map map, Map map2, Object obj) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "get", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "boolean", "java.lang.String", "java.util.Map", "java.util.Map", "java.lang.Object"}, "java.lang.String");
        return this.proxy.get(iRetrofit, z, str, map, map2, obj);
    }

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public byte[] postDataStream(IRetrofit iRetrofit, String str, Map map, TTHttpApi.TTHttpBody tTHttpBody, boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "postDataStream", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "java.lang.String", "java.util.Map", "com.bytedance.ttgame.main.internal.net.TTHttpApi$TTHttpBody", "boolean"}, "byte[]");
        return this.proxy.postDataStream(iRetrofit, str, map, tTHttpBody, z);
    }

    public TTHttpApi getProxy() {
        return this.proxy;
    }
}
