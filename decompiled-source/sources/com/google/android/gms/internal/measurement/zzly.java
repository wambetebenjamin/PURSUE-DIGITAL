package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [FieldDescriptorT] */
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
final class zzly<FieldDescriptorT> extends zzlv<FieldDescriptorT, Object> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzly() {
        super();
    }

    @Override // com.google.android.gms.internal.measurement.zzlv
    public final void zzd() {
        if (!zze()) {
            for (int i2 = 0; i2 < zza(); i2++) {
                Map.Entry<FieldDescriptorT, Object> zza = zza(i2);
                if (((zzjo) zza.getKey()).zze()) {
                    zza.setValue(Collections.unmodifiableList((List) zza.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorT, Object> entry : zzb()) {
                if (((zzjo) entry.getKey()).zze()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzd();
    }
}
