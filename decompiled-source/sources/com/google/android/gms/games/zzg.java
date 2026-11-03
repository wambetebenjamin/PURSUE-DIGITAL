package com.google.android.gms.games;

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public class zzg extends DataBufferRef {
    public zzg(DataHolder dataHolder, int i2) {
        super(dataHolder, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String zzj(String str, String str2) {
        if (!hasColumn(str) || hasNull(str)) {
            return null;
        }
        return getString(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzr(String str, int i2) {
        return (!hasColumn(str) || hasNull(str)) ? i2 : getInteger(str);
    }
}
