package com.bytedance.sync.util;

import android.util.Pair;
import com.ss.android.ugc.quota.BDNetworkTagManager;
/* compiled from: Utils.java */
/* loaded from: classes3.dex */
public class d {
    public static Pair<String, String> createNetworkTag(final boolean z) {
        try {
            if (Class.forName("com.ss.android.ugc.quota.BDNetworkTagManager") == null) {
                return null;
            }
            return BDNetworkTagManager.getInstance().buildBDNetworkTag(new com.ss.android.ugc.quota.a() { // from class: com.bytedance.sync.util.d.1
                @Override // com.ss.android.ugc.quota.a, com.ss.android.ugc.quota.c
                public int triggerType() {
                    return z ? 1 : 0;
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
