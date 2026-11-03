package com.bytedance.ttgame.main.internal.net;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import java.util.Map;
@InternalApi
/* loaded from: classes3.dex */
public interface TTHttpApi extends IModuleApi {
    String doPost(IRetrofit iRetrofit, String str, Map<String, String> map, TTHttpBody tTHttpBody) throws Exception;

    String doPost(IRetrofit iRetrofit, String str, Map<String, String> map, TTHttpBody tTHttpBody, boolean z) throws Exception;

    String doPost(IRetrofit iRetrofit, String str, Map<String, String> map, Map<String, String> map2) throws Exception;

    String get(IRetrofit iRetrofit, boolean z, String str, Map<String, String> map, Map<String, String> map2, Object obj) throws Exception;

    byte[] postDataStream(IRetrofit iRetrofit, String str, Map<String, String> map, TTHttpBody tTHttpBody, boolean z) throws Exception;

    /* loaded from: classes3.dex */
    public static class TTHttpBody {
        public byte[] data;
        public String type;

        public TTHttpBody(String str, byte[] bArr) {
            this.type = str;
            this.data = bArr;
        }
    }
}
