package com.bytedance.ttgame.module.notice;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.QueryMap;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: Request.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H'¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/module/notice/Request;", "", "fetchNotices", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/module/notice/FetchNoticesResponse;", "params", "", "", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface Request {
    @GET("/gsdk/announcement/list")
    Call<FetchNoticesResponse> fetchNotices(@QueryMap Map<String, String> map);
}
