package com.bytedance.ttgame.module.compliance.api.protocol;

import com.bytedance.apm.util.i;
import kotlin.Metadata;
/* compiled from: IProtocolService.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/protocol/LicenseUpdateType;", "", i.TAG, "", "(Ljava/lang/String;II)V", "LicenseUnUpdate", "ProtocolAndPrivacyUpdate", "ProtocolUpdate", "PrivacyUpdate", "compliance_api_protocol_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public enum LicenseUpdateType {
    LicenseUnUpdate(0),
    ProtocolAndPrivacyUpdate(1),
    ProtocolUpdate(2),
    PrivacyUpdate(3);

    LicenseUpdateType(int i2) {
    }
}
