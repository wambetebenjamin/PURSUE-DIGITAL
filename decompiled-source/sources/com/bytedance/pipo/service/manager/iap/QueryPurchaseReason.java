package com.bytedance.pipo.service.manager.iap;

import com.facebook.internal.AnalyticsEvents;
/* loaded from: classes2.dex */
public enum QueryPurchaseReason {
    AUTOMATIC(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_AUTOMATIC),
    USER_ACTION("user_action");
    
    public final String reason;

    QueryPurchaseReason(String str) {
        this.reason = str;
    }
}
