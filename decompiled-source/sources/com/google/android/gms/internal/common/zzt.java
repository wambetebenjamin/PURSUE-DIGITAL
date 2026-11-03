package com.google.android.gms.internal.common;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
final class zzt implements Iterable {
    final /* synthetic */ CharSequence zza;
    final /* synthetic */ zzw zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(zzw zzwVar, CharSequence charSequence) {
        this.zza = charSequence;
        Objects.requireNonNull(zzwVar);
        this.zzb = zzwVar;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.zzb.zze(this.zza);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator it = iterator();
        try {
            if (it.hasNext()) {
                sb.append(zzq.zza(it.next(), ", "));
                while (it.hasNext()) {
                    sb.append((CharSequence) ", ");
                    sb.append(zzq.zza(it.next(), ", "));
                }
            }
            sb.append(']');
            return sb.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
