package com.oasis.analytics;

import com.oasis.Logger.Logger;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class a extends AnalyticsAgent {
    @Override // com.oasis.analytics.AnalyticsAgent
    public void netDetect() {
    }

    @Override // com.oasis.analytics.AnalyticsAgent
    public void reportCreateRole(String str) {
        Logger.i("Oasis.Analytics", "DefaultAnalyticsAgent.reportCreateRole: " + str);
        AnalyticsAgent analyticsAgent = this.nextAgent;
        if (analyticsAgent != null) {
            analyticsAgent.reportCreateRole(str);
        }
    }

    @Override // com.oasis.analytics.AnalyticsAgent
    public void reportEnterZone(String str) {
        Logger.i("Oasis.Analytics", "DefaultAnalyticsAgent.reportEnterZone: " + str);
        AnalyticsAgent analyticsAgent = this.nextAgent;
        if (analyticsAgent != null) {
            analyticsAgent.reportEnterZone(str);
        }
    }

    @Override // com.oasis.analytics.AnalyticsAgent
    public void reportEvent(String str) {
        Logger.i("Oasis.Analytics", "DefaultAnalyticsAgent.reportEvent: " + str);
        AnalyticsAgent analyticsAgent = this.nextAgent;
        if (analyticsAgent != null) {
            analyticsAgent.reportEvent(str);
        }
    }

    @Override // com.oasis.analytics.AnalyticsAgent
    public void reportEvent(String str, JSONObject jSONObject) {
        Logger.i("Oasis.Analytics", "DefaultAnalyticsAgent.reportEvent: eventName == " + str + ", params == " + jSONObject);
    }

    @Override // com.oasis.analytics.AnalyticsAgent
    public void reportLoginRole(String str) {
        Logger.i("Oasis.Analytics", "DefaultAnalyticsAgent.reportLoginRole: " + str);
        AnalyticsAgent analyticsAgent = this.nextAgent;
        if (analyticsAgent != null) {
            analyticsAgent.reportLoginRole(str);
        }
    }

    @Override // com.oasis.analytics.AnalyticsAgent
    public void reportLogoutRole(String str) {
        Logger.i("Oasis.Analytics", "DefaultAnalyticsAgent.reportLogoutRole: " + str);
        AnalyticsAgent analyticsAgent = this.nextAgent;
        if (analyticsAgent != null) {
            analyticsAgent.reportLogoutRole(str);
        }
    }

    @Override // com.oasis.analytics.AnalyticsAgent
    public void reportRoleLevelUp(String str) {
        Logger.i("Oasis.Analytics", "DefaultAnalyticsAgent.reportRoleLevelUp: " + str);
        AnalyticsAgent analyticsAgent = this.nextAgent;
        if (analyticsAgent != null) {
            analyticsAgent.reportRoleLevelUp(str);
        }
    }
}
