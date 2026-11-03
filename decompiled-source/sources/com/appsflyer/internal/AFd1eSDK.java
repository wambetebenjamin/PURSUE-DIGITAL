package com.appsflyer.internal;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
/* loaded from: classes.dex */
public final class AFd1eSDK {
    public static final JSONArray values(List<AFd1oSDK> list) {
        Intrinsics.checkNotNullParameter(list, "");
        List<AFd1oSDK> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (AFd1oSDK aFd1oSDK : list2) {
            arrayList.add(aFd1oSDK.AFInAppEventParameterName());
        }
        return new JSONArray((Collection) arrayList);
    }

    public static final boolean values(HttpURLConnection httpURLConnection) {
        Intrinsics.checkNotNullParameter(httpURLConnection, "");
        return httpURLConnection.getResponseCode() / 100 == 2;
    }
}
