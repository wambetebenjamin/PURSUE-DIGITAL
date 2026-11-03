package com.bytedance.apm.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
/* compiled from: StringUtils.java */
/* loaded from: classes.dex */
public class ah {
    public static String slurp(InputStream inputStream) {
        char[] cArr = new char[8192];
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read >= 0) {
                sb.append(cArr, 0, read);
            } else {
                return sb.toString();
            }
        }
    }

    public static String join(Set<String> set, char c2) {
        return set == null ? "" : join(set.toArray(), c2, 0, set.size());
    }

    public static String join(Object[] objArr, char c2, int i2, int i3) {
        if (objArr == null) {
            return null;
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i4 * 16);
        for (int i5 = i2; i5 < i3; i5++) {
            if (i5 > i2) {
                sb.append(c2);
            }
            if (objArr[i5] != null) {
                sb.append(objArr[i5]);
            }
        }
        return sb.toString();
    }
}
