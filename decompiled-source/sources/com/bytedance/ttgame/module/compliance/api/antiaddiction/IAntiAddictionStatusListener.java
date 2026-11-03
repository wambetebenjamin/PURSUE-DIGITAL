package com.bytedance.ttgame.module.compliance.api.antiaddiction;

import kotlin.Metadata;
/* compiled from: IAntiAddictionStatusListener.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/antiaddiction/IAntiAddictionStatusListener;", "", "onChanged", "", "antiAddictionStatus", "Lcom/bytedance/ttgame/module/compliance/api/antiaddiction/AntiAddictionStatus;", "compliance_api_antiaddiction_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface IAntiAddictionStatusListener {
    void onChanged(AntiAddictionStatus antiAddictionStatus);
}
