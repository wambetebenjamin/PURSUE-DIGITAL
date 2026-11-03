package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public abstract class AssetPackStates {
    public static AssetPackStates a(Bundle bundle, co coVar, eb ebVar, be beVar) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        HashMap hashMap = new HashMap();
        int size = stringArrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str = stringArrayList.get(i2);
            hashMap.put(str, AssetPackState.b(bundle, str, coVar, ebVar, beVar));
        }
        return new bo(bundle.getLong("total_bytes_to_download"), hashMap);
    }

    public abstract Map<String, AssetPackState> packStates();

    public abstract long totalBytes();
}
