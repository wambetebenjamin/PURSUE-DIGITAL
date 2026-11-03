package com.twitter.sdk.android.core.internal.scribe;

import com.bytedance.bdinstall.e;
import com.bytedance.ttgame.module.dynamic.api.DynamicPageState;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes10.dex */
public class SyndicationClientEvent extends ScribeEvent {
    public static final String CLIENT_NAME = "tfw";
    private static final String SCRIBE_CATEGORY = "tfw_client_event";
    @SerializedName("event_info")
    public final String eventInfo;
    @SerializedName("external_ids")
    public final ExternalIds externalIds;
    @SerializedName(e.z)
    public final String language;

    public SyndicationClientEvent(EventNamespace eventNamespace, String str, long j2, String str2, String str3, List<ScribeItem> list) {
        super(SCRIBE_CATEGORY, eventNamespace, j2, list);
        this.language = str2;
        this.eventInfo = str;
        this.externalIds = new ExternalIds(str3);
    }

    /* loaded from: classes10.dex */
    public class ExternalIds {
        @SerializedName(DynamicPageState.BUNDLE_NOT_FOUND)
        public final String adId;

        public ExternalIds(String str) {
            this.adId = str;
        }
    }
}
