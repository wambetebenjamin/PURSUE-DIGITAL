package com.bytedance.ttgame.module.cdkey.api;

import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.gbridge.model.BridgeMsg;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.cdkey.api.GMGiftExchangeResult;
import kotlin.Metadata;
/* compiled from: ICDKeyService.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\f\rJ\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001a\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u000bH&¨\u0006\u000e"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/ICDKeyService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "cdkeyExchange", "", "cdKeyInfo", "Lcom/bytedance/ttgame/module/cdkey/api/CDKeyInfo;", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/module/cdkey/api/ICDKeyService$CDKeyExchangeCallback;", "gmGiftExchange", BridgeMsg.MSG_PARAM, "Lcom/bytedance/ttgame/module/cdkey/api/GMUserInfo;", "Lcom/bytedance/ttgame/module/cdkey/api/ICDKeyService$ExchangeCallback;", "CDKeyExchangeCallback", "ExchangeCallback", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface ICDKeyService extends IModuleApi {

    /* compiled from: ICDKeyService.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/ICDKeyService$CDKeyExchangeCallback;", "", "onFail", "", "error", "Lcom/bytedance/ttgame/base/GSDKError;", "onSuccess", "cdkey", "Lcom/bytedance/ttgame/module/cdkey/api/CDkeyExchangeResult;", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public interface CDKeyExchangeCallback {
        void onFail(GSDKError gSDKError);

        void onSuccess(CDkeyExchangeResult cDkeyExchangeResult);
    }

    /* compiled from: ICDKeyService.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0018\u00010\nR\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/ICDKeyService$ExchangeCallback;", "", "onFailed", "", "code", "", "errorMsg", "", "onSuccess", "data", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean;", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult;", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public interface ExchangeCallback {
        void onFailed(int i2, String str);

        void onSuccess(GMGiftExchangeResult.DataBean dataBean);
    }

    void cdkeyExchange(CDKeyInfo cDKeyInfo, CDKeyExchangeCallback cDKeyExchangeCallback);

    void gmGiftExchange(GMUserInfo gMUserInfo, ExchangeCallback exchangeCallback);
}
