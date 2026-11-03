package com.google.android.gms.internal.location;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public final class zzbn {
    public static String zza(@NullableDecl String str, @NullableDecl Object... objArr) {
        int length;
        int length2;
        int indexOf;
        String sb;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            length = objArr.length;
            if (i3 >= length) {
                break;
            }
            Object obj = objArr[i3];
            if (obj == null) {
                sb = "null";
            } else {
                try {
                    sb = obj.toString();
                } catch (Exception e2) {
                    String name = obj.getClass().getName();
                    String hexString = Integer.toHexString(System.identityHashCode(obj));
                    StringBuilder sb2 = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
                    sb2.append(name);
                    sb2.append('@');
                    sb2.append(hexString);
                    String sb3 = sb2.toString();
                    Logger logger = Logger.getLogger("com.google.common.base.Strings");
                    Level level = Level.WARNING;
                    String valueOf = String.valueOf(sb3);
                    logger.logp(level, "com.google.common.base.Strings", "lenientToString", valueOf.length() != 0 ? "Exception during lenientFormat for ".concat(valueOf) : new String("Exception during lenientFormat for "), (Throwable) e2);
                    String name2 = e2.getClass().getName();
                    StringBuilder sb4 = new StringBuilder(String.valueOf(sb3).length() + 9 + String.valueOf(name2).length());
                    sb4.append("<");
                    sb4.append(sb3);
                    sb4.append(" threw ");
                    sb4.append(name2);
                    sb4.append(">");
                    sb = sb4.toString();
                }
            }
            objArr[i3] = sb;
            i3++;
        }
        StringBuilder sb5 = new StringBuilder(str.length() + (length * 16));
        int i4 = 0;
        while (true) {
            length2 = objArr.length;
            if (i2 >= length2 || (indexOf = str.indexOf("%s", i4)) == -1) {
                break;
            }
            sb5.append((CharSequence) str, i4, indexOf);
            sb5.append(objArr[i2]);
            i4 = indexOf + 2;
            i2++;
        }
        sb5.append((CharSequence) str, i4, str.length());
        if (i2 < length2) {
            sb5.append(" [");
            sb5.append(objArr[i2]);
            for (int i5 = i2 + 1; i5 < objArr.length; i5++) {
                sb5.append(", ");
                sb5.append(objArr[i5]);
            }
            sb5.append(']');
        }
        return sb5.toString();
    }
}
