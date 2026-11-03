package com.bytedance.ttgame.core.net;

import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.mime.TypedByteArray;
import com.bytedance.ttgame.main.internal.net.IRetrofit;
import com.bytedance.ttgame.main.internal.net.TTHttpApi;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TTHttpApiImpl.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J@\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016JF\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\tH\u0016JY\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002JB\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u0019"}, d2 = {"Lcom/bytedance/ttgame/core/net/TTHttpApiImpl;", "Lcom/bytedance/ttgame/main/internal/net/TTHttpApi;", "()V", "doPost", "", "retrofit", "Lcom/bytedance/ttgame/main/internal/net/IRetrofit;", "url", "headerMap", "", "body", "Lcom/bytedance/ttgame/main/internal/net/TTHttpApi$TTHttpBody;", "addCommonParas", "", "map", "get", "addCommonPara", "relativePath", "queryMap", "extraInfo", "", "postDataStream", "", "Companion", "SingletonHolder", "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TTHttpApiImpl implements TTHttpApi {
    public static final Companion Companion = new Companion(null);
    private static final TTHttpApiImpl instance = SingletonHolder.INSTANCE.getHolder();
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    /* compiled from: TTHttpApiImpl.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/bytedance/ttgame/core/net/TTHttpApiImpl$Companion;", "", "()V", "instance", "Lcom/bytedance/ttgame/core/net/TTHttpApiImpl;", "getInstance", "()Lcom/bytedance/ttgame/core/net/TTHttpApiImpl;", "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TTHttpApiImpl getInstance() {
            return TTHttpApiImpl.instance;
        }
    }

    /* compiled from: TTHttpApiImpl.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/bytedance/ttgame/core/net/TTHttpApiImpl$SingletonHolder;", "", "()V", "holder", "Lcom/bytedance/ttgame/core/net/TTHttpApiImpl;", "getHolder", "()Lcom/bytedance/ttgame/core/net/TTHttpApiImpl;", "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    static final class SingletonHolder {
        public static final SingletonHolder INSTANCE = new SingletonHolder();
        private static final TTHttpApiImpl holder = new TTHttpApiImpl();

        private SingletonHolder() {
        }

        public final TTHttpApiImpl getHolder() {
            return holder;
        }
    }

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public String get(IRetrofit retrofit, boolean z, String str, Map<String, String> map, Map<String, String> map2, Object obj) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "get", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "boolean", "java.lang.String", "java.util.Map", "java.util.Map", "java.lang.Object"}, "java.lang.String");
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        TTHttpRetrofitApi tTHttpRetrofitApi = (TTHttpRetrofitApi) retrofit.create(TTHttpRetrofitApi.class);
        ArrayList arrayList = new ArrayList();
        if (map2 != null) {
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                arrayList.add(new Header(entry.getKey(), entry.getValue()));
            }
        }
        String body = tTHttpRetrofitApi.get(z, str, map, arrayList, obj).execute().body();
        Intrinsics.checkNotNullExpressionValue(body, "retrofitApi.get(addCommo…                  .body()");
        return body;
    }

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public String doPost(IRetrofit retrofit, String str, Map<String, String> map, Map<String, String> map2) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "doPost", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "java.lang.String", "java.util.Map", "java.util.Map"}, "java.lang.String");
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        TTHttpRetrofitApi tTHttpRetrofitApi = (TTHttpRetrofitApi) retrofit.create(TTHttpRetrofitApi.class);
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(new Header(entry.getKey(), entry.getValue()));
            }
        }
        String body = tTHttpRetrofitApi.doPost(str, arrayList, map2).execute().body();
        Intrinsics.checkNotNullExpressionValue(body, "retrofitApi.doPost(url, …rs, map).execute().body()");
        return body;
    }

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public String doPost(IRetrofit retrofit, String str, Map<String, String> map, TTHttpApi.TTHttpBody body) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "doPost", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "java.lang.String", "java.util.Map", "com.bytedance.ttgame.main.internal.net.TTHttpApi$TTHttpBody"}, "java.lang.String");
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Intrinsics.checkNotNullParameter(body, "body");
        TTHttpRetrofitApi tTHttpRetrofitApi = (TTHttpRetrofitApi) retrofit.create(TTHttpRetrofitApi.class);
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(new Header(entry.getKey(), entry.getValue()));
            }
        }
        String body2 = tTHttpRetrofitApi.doPost(str, arrayList, new TypedByteArray(body.type, body.data, new String[0])).execute().body();
        Intrinsics.checkNotNullExpressionValue(body2, "retrofitApi.doPost(url, …y.data)).execute().body()");
        return body2;
    }

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public String doPost(IRetrofit retrofit, String str, Map<String, String> map, TTHttpApi.TTHttpBody body, boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "doPost", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "java.lang.String", "java.util.Map", "com.bytedance.ttgame.main.internal.net.TTHttpApi$TTHttpBody", "boolean"}, "java.lang.String");
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Intrinsics.checkNotNullParameter(body, "body");
        TTHttpRetrofitApi tTHttpRetrofitApi = (TTHttpRetrofitApi) retrofit.create(TTHttpRetrofitApi.class);
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(new Header(entry.getKey(), entry.getValue()));
            }
        }
        String body2 = tTHttpRetrofitApi.doPost(str, arrayList, new TypedByteArray(body.type, body.data, new String[0]), z).execute().body();
        Intrinsics.checkNotNullExpressionValue(body2, "retrofitApi.doPost(url, …onParas).execute().body()");
        return body2;
    }

    @Override // com.bytedance.ttgame.main.internal.net.TTHttpApi
    public byte[] postDataStream(IRetrofit retrofit, String str, Map<String, String> map, TTHttpApi.TTHttpBody body, boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.TTHttpApi", "com.bytedance.ttgame.core.net.TTHttpApiImpl", "postDataStream", new String[]{"com.bytedance.ttgame.main.internal.net.IRetrofit", "java.lang.String", "java.util.Map", "com.bytedance.ttgame.main.internal.net.TTHttpApi$TTHttpBody", "boolean"}, "byte[]");
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Intrinsics.checkNotNullParameter(body, "body");
        TTHttpRetrofitApi tTHttpRetrofitApi = (TTHttpRetrofitApi) retrofit.create(TTHttpRetrofitApi.class);
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(new Header(entry.getKey(), entry.getValue()));
            }
        }
        InputStream in = tTHttpRetrofitApi.postDataStream(str, arrayList, new TypedByteArray(body.type, body.data, new String[0]), z).execute().body().in();
        byte[] readBytes = ByteStreamsKt.readBytes(in);
        in.close();
        return readBytes;
    }
}
