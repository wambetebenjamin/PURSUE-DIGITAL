package com.google.android.gms.internal.play_billing;

import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzbm {
    public static String zza(String str) {
        if (zzbi.zza(str)) {
            return null;
        }
        return str;
    }

    public static String zzb(String str, Object... objArr) {
        int length;
        int indexOf;
        StringBuilder sb = new StringBuilder(str.length() + (objArr.length * 16));
        int i2 = 0;
        int i3 = 0;
        while (true) {
            length = objArr.length;
            if (i2 >= length || (indexOf = str.indexOf("%s", i3)) == -1) {
                break;
            }
            sb.append((CharSequence) str, i3, indexOf);
            sb.append(zze(objArr[i2]));
            i3 = indexOf + 2;
            i2++;
        }
        sb.append((CharSequence) str, i3, str.length());
        if (i2 < length) {
            String str2 = " [";
            while (i2 < objArr.length) {
                sb.append(str2);
                sb.append(zze(objArr[i2]));
                i2++;
                str2 = ", ";
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public static String zzc(String str) {
        return str == null ? "" : str;
    }

    public static boolean zzd(String str) {
        return zzbi.zza(str);
    }

    private static String zze(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return obj.toString();
        } catch (Exception e2) {
            String str = obj.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(obj));
            Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(str), (Throwable) e2);
            return "<" + str + " threw " + e2.getClass().getName() + ">";
        }
    }
}
