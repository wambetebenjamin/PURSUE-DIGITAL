package com.google.android.gms.internal.games_v2;

import java.util.Iterator;
import java.util.Set;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzgy {
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
