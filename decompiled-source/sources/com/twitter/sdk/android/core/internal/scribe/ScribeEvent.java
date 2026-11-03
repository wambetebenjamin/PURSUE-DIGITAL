package com.twitter.sdk.android.core.internal.scribe;

import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.meituan.robust.Constants;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public class ScribeEvent {
    private static final String CURRENT_FORMAT_VERSION = "2";
    @SerializedName("_category_")
    final String category;
    @SerializedName("event_namespace")
    final EventNamespace eventNamespace;
    @SerializedName("format_version")
    final String formatVersion;
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    final List<ScribeItem> items;
    @SerializedName("ts")
    final String timestamp;

    public ScribeEvent(String str, EventNamespace eventNamespace, long j2) {
        this(str, eventNamespace, j2, Collections.emptyList());
    }

    public ScribeEvent(String str, EventNamespace eventNamespace, long j2, List<ScribeItem> list) {
        this.category = str;
        this.eventNamespace = eventNamespace;
        this.timestamp = String.valueOf(j2);
        this.formatVersion = "2";
        this.items = Collections.unmodifiableList(list);
    }

    public String toString() {
        return "event_namespace=" + this.eventNamespace + ", ts=" + this.timestamp + ", format_version=" + this.formatVersion + ", _category_=" + this.category + ", items=" + (Constants.ARRAY_TYPE + TextUtils.join(", ", this.items) + "]");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScribeEvent scribeEvent = (ScribeEvent) obj;
        String str = this.category;
        if (str == null ? scribeEvent.category == null : str.equals(scribeEvent.category)) {
            EventNamespace eventNamespace = this.eventNamespace;
            if (eventNamespace == null ? scribeEvent.eventNamespace == null : eventNamespace.equals(scribeEvent.eventNamespace)) {
                String str2 = this.formatVersion;
                if (str2 == null ? scribeEvent.formatVersion == null : str2.equals(scribeEvent.formatVersion)) {
                    String str3 = this.timestamp;
                    if (str3 == null ? scribeEvent.timestamp == null : str3.equals(scribeEvent.timestamp)) {
                        List<ScribeItem> list = this.items;
                        List<ScribeItem> list2 = scribeEvent.items;
                        return list == null ? list2 == null : list.equals(list2);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        EventNamespace eventNamespace = this.eventNamespace;
        int hashCode = (eventNamespace != null ? eventNamespace.hashCode() : 0) * 31;
        String str = this.timestamp;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.formatVersion;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.category;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        List<ScribeItem> list = this.items;
        return hashCode4 + (list != null ? list.hashCode() : 0);
    }

    /* loaded from: classes10.dex */
    public static class Transform implements EventTransform<ScribeEvent> {
        private final Gson gson;

        public Transform(Gson gson) {
            this.gson = gson;
        }

        @Override // com.twitter.sdk.android.core.internal.scribe.EventTransform
        public byte[] toBytes(ScribeEvent scribeEvent) throws IOException {
            return this.gson.toJson(scribeEvent).getBytes("UTF-8");
        }
    }
}
