package com.vk.api.sdk;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
/* compiled from: VKOkHttpProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0002\t\nB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lcom/vk/api/sdk/VKOkHttpProvider;", "", "()V", "getClient", "Lokhttp3/OkHttpClient;", "updateClient", "", "f", "Lcom/vk/api/sdk/VKOkHttpProvider$BuilderUpdateFunction;", "BuilderUpdateFunction", "DefaultProvider", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public abstract class VKOkHttpProvider {

    /* compiled from: VKOkHttpProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/vk/api/sdk/VKOkHttpProvider$BuilderUpdateFunction;", "", "update", "Lokhttp3/OkHttpClient$Builder;", "builder", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public interface BuilderUpdateFunction {
        OkHttpClient.Builder update(OkHttpClient.Builder builder);
    }

    public abstract OkHttpClient getClient();

    public abstract void updateClient(BuilderUpdateFunction builderUpdateFunction);

    /* compiled from: VKOkHttpProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/vk/api/sdk/VKOkHttpProvider$DefaultProvider;", "Lcom/vk/api/sdk/VKOkHttpProvider;", "()V", "okHttpClient", "Lokhttp3/OkHttpClient;", "getClient", "updateClient", "", "f", "Lcom/vk/api/sdk/VKOkHttpProvider$BuilderUpdateFunction;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class DefaultProvider extends VKOkHttpProvider {
        private volatile OkHttpClient okHttpClient;

        @Override // com.vk.api.sdk.VKOkHttpProvider
        public OkHttpClient getClient() {
            if (this.okHttpClient == null) {
                this.okHttpClient = new OkHttpClient().newBuilder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).followRedirects(true).followSslRedirects(true).build();
            }
            OkHttpClient okHttpClient = this.okHttpClient;
            if (okHttpClient == null) {
                Intrinsics.throwNpe();
            }
            return okHttpClient;
        }

        @Override // com.vk.api.sdk.VKOkHttpProvider
        public void updateClient(BuilderUpdateFunction f2) {
            Intrinsics.checkParameterIsNotNull(f2, "f");
            if (this.okHttpClient != null) {
                OkHttpClient okHttpClient = this.okHttpClient;
                if (okHttpClient == null) {
                    Intrinsics.throwNpe();
                }
                OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
                Intrinsics.checkExpressionValueIsNotNull(newBuilder, "okHttpClient!!.newBuilder()");
                this.okHttpClient = f2.update(newBuilder).build();
            }
        }
    }
}
