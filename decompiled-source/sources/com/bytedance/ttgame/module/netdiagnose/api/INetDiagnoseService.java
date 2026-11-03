package com.bytedance.ttgame.module.netdiagnose.api;

import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.netdiagnose.api.data.IpAddressResult;
import kotlin.Metadata;
/* compiled from: INetDiagnoseService.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/module/netdiagnose/api/INetDiagnoseService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "getIPAddress", "Lcom/bytedance/ttgame/module/netdiagnose/api/data/IpAddressResult;", "context", "Landroid/content/Context;", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface INetDiagnoseService extends IModuleApi {
    IpAddressResult getIPAddress(Context context);
}
