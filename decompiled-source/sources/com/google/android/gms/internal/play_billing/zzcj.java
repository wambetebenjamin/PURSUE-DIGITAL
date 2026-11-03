package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
import java.util.Set;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzcj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(Set set) {
        Iterator it = set.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i2 += next != null ? next.hashCode() : 0;
        }
        return i2;
    }
}
