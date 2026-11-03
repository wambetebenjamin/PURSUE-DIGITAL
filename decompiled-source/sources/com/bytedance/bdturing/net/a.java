package com.bytedance.bdturing.net;

import com.bytedance.bdturing.net.models.b;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: HttpClient.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007H&J0\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/bytedance/bdturing/net/HttpClient;", "", "get", "Lcom/bytedance/bdturing/net/models/NativeResponse;", "url", "", "headers", "", com.bytedance.sdk.account.execute.a.METHOD_POST, "body", "", "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface a {
    b a(String str, Map<String, String> map);

    b a(String str, Map<String, String> map, byte[] bArr);

    /* compiled from: HttpClient.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* renamed from: com.bytedance.bdturing.net.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0052a {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ b a(a aVar, String str, Map map, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    map = null;
                }
                return aVar.a(str, map);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ b a(a aVar, String str, Map map, byte[] bArr, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    map = null;
                }
                return aVar.a(str, map, bArr);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: post");
        }
    }
}
