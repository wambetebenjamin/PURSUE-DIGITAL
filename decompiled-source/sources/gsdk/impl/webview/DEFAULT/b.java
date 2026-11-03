package gsdk.impl.webview.DEFAULT;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.Url;
import com.bytedance.ttgame.module.bridge.model.WhiteListInfo;
/* compiled from: IBridgeApi.java */
/* loaded from: classes10.dex */
public interface b {
    @GET
    Call<WhiteListInfo> a(@AddCommonParam boolean z, @Url String str);
}
