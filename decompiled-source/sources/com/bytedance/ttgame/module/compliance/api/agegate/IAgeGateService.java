package com.bytedance.ttgame.module.compliance.api.agegate;

import android.app.Activity;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
/* loaded from: classes4.dex */
public interface IAgeGateService extends IModuleApi {
    void ageGate(Activity activity, IAgeGateCallback iAgeGateCallback);

    void checkSensitiveFeatureAccess(SensitiveFeatureAgeCheckRequest sensitiveFeatureAgeCheckRequest, IAgeGateResultCallback<SensitiveFeatureAgeCheckResult> iAgeGateResultCallback);

    @InternalApi
    int getAgeLimitWithDefaultStrategy();

    @InternalApi
    boolean getCheckboxDefaultStatus();

    @InternalApi
    boolean getShouldShowAgeConfirmInfo();

    @InternalApi
    int getWindowTypeWithDefaultStrategy();

    void judgeAgeGate(IAgeGateResultCallback<AgeGateResult> iAgeGateResultCallback);

    void modifyAgeGateStatus(boolean z);

    boolean queryAgeGateStatus();

    @InternalApi
    default void modifyAgeGateStatus(boolean z, boolean z2, String str) {
        modifyAgeGateStatus(z);
    }
}
