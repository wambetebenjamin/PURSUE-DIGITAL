package com.bytedance.ttgame.module.account.bridge.model;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.rocketapi.account.AgeGateResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMAgeGateResult.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"transformAgeGateResult", "Lcom/bytedance/ttgame/module/account/bridge/model/GMAgeGateResult;", "ageGateResult", "Lcom/bytedance/ttgame/rocketapi/account/AgeGateResult;", "account_bridge_i18nRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMAgeGateResultKt {
    public static final GMAgeGateResult transformAgeGateResult(AgeGateResult ageGateResult) {
        Intrinsics.checkNotNullParameter(ageGateResult, "ageGateResult");
        GSDKError gsdkError = ageGateResult.getGsdkError();
        int code = gsdkError != null ? gsdkError.getCode() : -105999;
        GSDKError gsdkError2 = ageGateResult.getGsdkError();
        String message = gsdkError2 != null ? gsdkError2.getMessage() : null;
        String str = message == null ? "" : message;
        GSDKError gsdkError3 = ageGateResult.getGsdkError();
        int extraErrorCode = gsdkError3 != null ? gsdkError3.getExtraErrorCode() : 0;
        GSDKError gsdkError4 = ageGateResult.getGsdkError();
        String extraErrorMessage = gsdkError4 != null ? gsdkError4.getExtraErrorMessage() : null;
        String str2 = extraErrorMessage == null ? "" : extraErrorMessage;
        GSDKError gsdkError5 = ageGateResult.getGsdkError();
        String additionalInfo = gsdkError5 != null ? gsdkError5.getAdditionalInfo() : null;
        return new GMAgeGateResult(code, str, extraErrorCode, str2, additionalInfo == null ? "" : additionalInfo, ageGateResult.getCurrentTimeStamp(), ageGateResult.getWindowType(), ageGateResult.getLimitAge(), ageGateResult.isInCoolingPeriod());
    }
}
