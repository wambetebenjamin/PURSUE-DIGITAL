package com.bytedance.ttgame.module.account.qrcode.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Path;
import com.bytedance.ttgame.base.BaseResponse;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: IQRCodeApi.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\u0016\b\u0001\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nH'J:\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\u0016\b\u0001\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nH'¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/module/account/qrcode/api/IQRCodeApi;", "", "confirmQRCode", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/base/BaseResponse;", "path", "", "addCommonPara", "", "map", "", "scanQRCode", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface IQRCodeApi {
    @FormUrlEncoded
    @POST("{path}/account/confirm_qrcode")
    Call<BaseResponse> confirmQRCode(@Path("path") String str, @AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("{path}/account/scan_qrcode")
    Call<BaseResponse> scanQRCode(@Path("path") String str, @AddCommonParam boolean z, @FieldMap Map<String, Object> map);
}
