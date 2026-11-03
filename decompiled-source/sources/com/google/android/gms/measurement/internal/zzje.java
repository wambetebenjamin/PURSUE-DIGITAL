package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.EnumMap;
import java.util.Map;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzje {
    public static final zzje zza = new zzje(null, null, 100);
    private final EnumMap<zza, zzjh> zzb;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(int i2) {
        return i2 != -30 ? i2 != -20 ? i2 != -10 ? i2 != 0 ? i2 != 30 ? i2 != 90 ? i2 != 100 ? "OTHER" : "UNKNOWN" : "REMOTE_CONFIG" : "1P_INIT" : "1P_API" : "MANIFEST" : "API" : "TCF";
    }

    public static boolean zza(int i2, int i3) {
        return ((i2 == -20 && i3 == -30) || ((i2 == -30 && i3 == -20) || i2 == i3)) || i2 < i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static char zza(zzjh zzjhVar) {
        if (zzjhVar != null) {
            int ordinal = zzjhVar.ordinal();
            if (ordinal != 1) {
                if (ordinal != 2) {
                    return ordinal != 3 ? '-' : '1';
                }
                return '0';
            }
            return '+';
        }
        return '-';
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
    /* loaded from: classes5.dex */
    public enum zza {
        AD_STORAGE("ad_storage"),
        ANALYTICS_STORAGE("analytics_storage"),
        AD_USER_DATA("ad_user_data"),
        AD_PERSONALIZATION("ad_personalization");
        
        public final String zze;

        zza(String str) {
            this.zze = str;
        }
    }

    public final int zza() {
        return this.zzc;
    }

    public final int hashCode() {
        int i2 = this.zzc * 17;
        for (zzjh zzjhVar : this.zzb.values()) {
            i2 = (i2 * 31) + zzjhVar.hashCode();
        }
        return i2;
    }

    public final Bundle zzb() {
        Bundle bundle = new Bundle();
        for (Map.Entry<zza, zzjh> entry : this.zzb.entrySet()) {
            String zzb = zzb(entry.getValue());
            if (zzb != null) {
                bundle.putString(entry.getKey().zze, zzb);
            }
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjh zza(String str) {
        if (str == null) {
            return zzjh.UNINITIALIZED;
        }
        if (str.equals("granted")) {
            return zzjh.GRANTED;
        }
        if (str.equals("denied")) {
            return zzjh.DENIED;
        }
        return zzjh.UNINITIALIZED;
    }

    public final zzjh zzc() {
        zzjh zzjhVar = this.zzb.get(zza.AD_STORAGE);
        return zzjhVar == null ? zzjh.UNINITIALIZED : zzjhVar;
    }

    public final zzjh zzd() {
        zzjh zzjhVar = this.zzb.get(zza.ANALYTICS_STORAGE);
        return zzjhVar == null ? zzjh.UNINITIALIZED : zzjhVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjh zza(char c2) {
        if (c2 != '+') {
            if (c2 != '0') {
                if (c2 == '1') {
                    return zzjh.GRANTED;
                }
                return zzjh.UNINITIALIZED;
            }
            return zzjh.DENIED;
        }
        return zzjh.POLICY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjh zza(Boolean bool) {
        if (bool == null) {
            return zzjh.UNINITIALIZED;
        }
        if (bool.booleanValue()) {
            return zzjh.GRANTED;
        }
        return zzjh.DENIED;
    }

    public static zzje zza(Bundle bundle, int i2) {
        zza[] zzaVarArr;
        if (bundle == null) {
            return new zzje(null, null, i2);
        }
        EnumMap enumMap = new EnumMap(zza.class);
        zzaVarArr = zzjg.STORAGE.zzd;
        for (zza zzaVar : zzaVarArr) {
            enumMap.put((EnumMap) zzaVar, (zza) zza(bundle.getString(zzaVar.zze)));
        }
        return new zzje(enumMap, i2);
    }

    public static zzje zza(zzjh zzjhVar, zzjh zzjhVar2, int i2) {
        EnumMap enumMap = new EnumMap(zza.class);
        enumMap.put((EnumMap) zza.AD_STORAGE, (zza) zzjhVar);
        enumMap.put((EnumMap) zza.ANALYTICS_STORAGE, (zza) zzjhVar2);
        return new zzje(enumMap, -10);
    }

    public static zzje zzb(String str) {
        return zza(str, 100);
    }

    public static zzje zza(String str, int i2) {
        EnumMap enumMap = new EnumMap(zza.class);
        if (str == null) {
            str = "";
        }
        zza[] zza2 = zzjg.STORAGE.zza();
        for (int i3 = 0; i3 < zza2.length; i3++) {
            zza zzaVar = zza2[i3];
            int i4 = i3 + 2;
            if (i4 < str.length()) {
                enumMap.put((EnumMap) zzaVar, (zza) zza(str.charAt(i4)));
            } else {
                enumMap.put((EnumMap) zzaVar, (zza) zzjh.UNINITIALIZED);
            }
        }
        return new zzje(enumMap, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0051 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzje zza(com.google.android.gms.measurement.internal.zzje r9) {
        /*
            r8 = this;
            java.util.EnumMap r0 = new java.util.EnumMap
            java.lang.Class<com.google.android.gms.measurement.internal.zzje$zza> r1 = com.google.android.gms.measurement.internal.zzje.zza.class
            r0.<init>(r1)
            com.google.android.gms.measurement.internal.zzjg r1 = com.google.android.gms.measurement.internal.zzjg.STORAGE
            com.google.android.gms.measurement.internal.zzje$zza[] r1 = com.google.android.gms.measurement.internal.zzjg.zza(r1)
            int r2 = r1.length
            r3 = 0
        Lf:
            if (r3 >= r2) goto L54
            r4 = r1[r3]
            java.util.EnumMap<com.google.android.gms.measurement.internal.zzje$zza, com.google.android.gms.measurement.internal.zzjh> r5 = r8.zzb
            java.lang.Object r5 = r5.get(r4)
            com.google.android.gms.measurement.internal.zzjh r5 = (com.google.android.gms.measurement.internal.zzjh) r5
            java.util.EnumMap<com.google.android.gms.measurement.internal.zzje$zza, com.google.android.gms.measurement.internal.zzjh> r6 = r9.zzb
            java.lang.Object r6 = r6.get(r4)
            com.google.android.gms.measurement.internal.zzjh r6 = (com.google.android.gms.measurement.internal.zzjh) r6
            if (r5 != 0) goto L26
            goto L37
        L26:
            if (r6 != 0) goto L29
            goto L4c
        L29:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.UNINITIALIZED
            if (r5 != r7) goto L2e
            goto L37
        L2e:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.UNINITIALIZED
            if (r6 != r7) goto L33
            goto L4c
        L33:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.POLICY
            if (r5 != r7) goto L39
        L37:
            r5 = r6
            goto L4c
        L39:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.POLICY
            if (r6 != r7) goto L3e
            goto L4c
        L3e:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.DENIED
            if (r5 == r7) goto L4a
            com.google.android.gms.measurement.internal.zzjh r5 = com.google.android.gms.measurement.internal.zzjh.DENIED
            if (r6 != r5) goto L47
            goto L4a
        L47:
            com.google.android.gms.measurement.internal.zzjh r5 = com.google.android.gms.measurement.internal.zzjh.GRANTED
            goto L4c
        L4a:
            com.google.android.gms.measurement.internal.zzjh r5 = com.google.android.gms.measurement.internal.zzjh.DENIED
        L4c:
            if (r5 == 0) goto L51
            r0.put(r4, r5)
        L51:
            int r3 = r3 + 1
            goto Lf
        L54:
            com.google.android.gms.measurement.internal.zzje r9 = new com.google.android.gms.measurement.internal.zzje
            r1 = 100
            r9.<init>(r0, r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzje.zza(com.google.android.gms.measurement.internal.zzje):com.google.android.gms.measurement.internal.zzje");
    }

    public final zzje zzb(zzje zzjeVar) {
        zza[] zzaVarArr;
        EnumMap enumMap = new EnumMap(zza.class);
        zzaVarArr = zzjg.STORAGE.zzd;
        for (zza zzaVar : zzaVarArr) {
            zzjh zzjhVar = this.zzb.get(zzaVar);
            if (zzjhVar == zzjh.UNINITIALIZED) {
                zzjhVar = zzjeVar.zzb.get(zzaVar);
            }
            if (zzjhVar != null) {
                enumMap.put((EnumMap) zzaVar, (zza) zzjhVar);
            }
        }
        return new zzje(enumMap, this.zzc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzb(zzjh zzjhVar) {
        int ordinal = zzjhVar.ordinal();
        if (ordinal != 2) {
            if (ordinal != 3) {
                return null;
            }
            return "granted";
        }
        return "denied";
    }

    public static String zza(Bundle bundle) {
        zza[] zzaVarArr;
        String string;
        zzaVarArr = zzjg.STORAGE.zzd;
        int length = zzaVarArr.length;
        int i2 = 0;
        while (true) {
            Boolean bool = null;
            if (i2 >= length) {
                return null;
            }
            zza zzaVar = zzaVarArr[i2];
            if (bundle.containsKey(zzaVar.zze) && (string = bundle.getString(zzaVar.zze)) != null) {
                if (string != null) {
                    if (string.equals("granted")) {
                        bool = Boolean.TRUE;
                    } else if (string.equals("denied")) {
                        bool = Boolean.FALSE;
                    }
                }
                if (bool == null) {
                    return string;
                }
            }
            i2++;
        }
    }

    public final String zze() {
        int ordinal;
        StringBuilder sb = new StringBuilder("G1");
        for (zza zzaVar : zzjg.STORAGE.zza()) {
            zzjh zzjhVar = this.zzb.get(zzaVar);
            char c2 = '-';
            if (zzjhVar != null && (ordinal = zzjhVar.ordinal()) != 0) {
                if (ordinal != 1) {
                    if (ordinal == 2) {
                        c2 = '0';
                    } else if (ordinal != 3) {
                    }
                }
                c2 = '1';
            }
            sb.append(c2);
        }
        return sb.toString();
    }

    public final String zzf() {
        StringBuilder sb = new StringBuilder("G1");
        for (zza zzaVar : zzjg.STORAGE.zza()) {
            sb.append(zza(this.zzb.get(zzaVar)));
        }
        return sb.toString();
    }

    public final String toString() {
        zza[] zzaVarArr;
        StringBuilder append = new StringBuilder("source=").append(zza(this.zzc));
        zzaVarArr = zzjg.STORAGE.zzd;
        for (zza zzaVar : zzaVarArr) {
            append.append(",");
            append.append(zzaVar.zze);
            append.append("=");
            zzjh zzjhVar = this.zzb.get(zzaVar);
            if (zzjhVar == null) {
                zzjhVar = zzjh.UNINITIALIZED;
            }
            append.append(zzjhVar);
        }
        return append.toString();
    }

    private zzje(EnumMap<zza, zzjh> enumMap, int i2) {
        EnumMap<zza, zzjh> enumMap2 = new EnumMap<>(zza.class);
        this.zzb = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzc = i2;
    }

    public zzje(Boolean bool, Boolean bool2, int i2) {
        EnumMap<zza, zzjh> enumMap = new EnumMap<>(zza.class);
        this.zzb = enumMap;
        enumMap.put((EnumMap<zza, zzjh>) zza.AD_STORAGE, (zza) zza((Boolean) null));
        enumMap.put((EnumMap<zza, zzjh>) zza.ANALYTICS_STORAGE, (zza) zza((Boolean) null));
        this.zzc = i2;
    }

    public final boolean equals(Object obj) {
        zza[] zzaVarArr;
        if (obj instanceof zzje) {
            zzje zzjeVar = (zzje) obj;
            zzaVarArr = zzjg.STORAGE.zzd;
            for (zza zzaVar : zzaVarArr) {
                if (this.zzb.get(zzaVar) != zzjeVar.zzb.get(zzaVar)) {
                    return false;
                }
            }
            return this.zzc == zzjeVar.zzc;
        }
        return false;
    }

    public final boolean zza(zzje zzjeVar, zza... zzaVarArr) {
        for (zza zzaVar : zzaVarArr) {
            if (!zzjeVar.zza(zzaVar) && zza(zzaVar)) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzg() {
        return zza(zza.AD_STORAGE);
    }

    public final boolean zza(zza zzaVar) {
        return this.zzb.get(zzaVar) != zzjh.DENIED;
    }

    public final boolean zzh() {
        return zza(zza.ANALYTICS_STORAGE);
    }

    public final boolean zzi() {
        for (zzjh zzjhVar : this.zzb.values()) {
            if (zzjhVar != zzjh.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzc(zzje zzjeVar) {
        return zzb(zzjeVar, (zza[]) this.zzb.keySet().toArray(new zza[0]));
    }

    public final boolean zzb(zzje zzjeVar, zza... zzaVarArr) {
        for (zza zzaVar : zzaVarArr) {
            zzjh zzjhVar = this.zzb.get(zzaVar);
            zzjh zzjhVar2 = zzjeVar.zzb.get(zzaVar);
            if (zzjhVar == zzjh.DENIED && zzjhVar2 != zzjh.DENIED) {
                return true;
            }
        }
        return false;
    }
}
