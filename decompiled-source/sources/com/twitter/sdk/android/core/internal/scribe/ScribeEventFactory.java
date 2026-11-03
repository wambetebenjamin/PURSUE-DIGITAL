package com.twitter.sdk.android.core.internal.scribe;

import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public class ScribeEventFactory {
    public static ScribeEvent newScribeEvent(EventNamespace eventNamespace, long j2, String str, String str2) {
        return newScribeEvent(eventNamespace, "", j2, str, str2, Collections.emptyList());
    }

    public static ScribeEvent newScribeEvent(EventNamespace eventNamespace, String str, long j2, String str2, String str3, List<ScribeItem> list) {
        String str4 = eventNamespace.client;
        str4.hashCode();
        if (str4.equals(SyndicationClientEvent.CLIENT_NAME)) {
            return new SyndicationClientEvent(eventNamespace, str, j2, str2, str3, list);
        }
        return new SyndicatedSdkImpressionEvent(eventNamespace, j2, str2, str3, list);
    }
}
