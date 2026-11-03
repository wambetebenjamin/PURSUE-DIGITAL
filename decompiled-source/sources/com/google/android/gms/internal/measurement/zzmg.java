package com.google.android.gms.internal.measurement;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
final class zzmg {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzik zzikVar) {
        zzmf zzmfVar = new zzmf(zzikVar);
        StringBuilder sb = new StringBuilder(zzmfVar.zza());
        for (int i2 = 0; i2 < zzmfVar.zza(); i2++) {
            byte zza = zzmfVar.zza(i2);
            if (zza == 34) {
                sb.append("\\\"");
            } else if (zza == 39) {
                sb.append("\\'");
            } else if (zza != 92) {
                switch (zza) {
                    case 7:
                        sb.append("\\a");
                        continue;
                    case 8:
                        sb.append("\\b");
                        continue;
                    case 9:
                        sb.append("\\t");
                        continue;
                    case 10:
                        sb.append("\\n");
                        continue;
                    case 11:
                        sb.append("\\v");
                        continue;
                    case 12:
                        sb.append("\\f");
                        continue;
                    case 13:
                        sb.append("\\r");
                        continue;
                    default:
                        if (zza >= 32 && zza <= 126) {
                            sb.append((char) zza);
                            continue;
                        } else {
                            sb.append('\\');
                            sb.append((char) (((zza >>> 6) & 3) + 48));
                            sb.append((char) (((zza >>> 3) & 7) + 48));
                            sb.append((char) ((zza & 7) + 48));
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
