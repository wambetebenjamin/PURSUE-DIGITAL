package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzcb {
    public static Object zza(Iterable iterable, Object obj) {
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }
}
