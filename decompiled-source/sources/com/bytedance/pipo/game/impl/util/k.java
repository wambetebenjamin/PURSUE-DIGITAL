package com.bytedance.pipo.game.impl.util;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* compiled from: StringUtils.java */
/* loaded from: classes2.dex */
public class k {
    public static String a() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static String a(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && !list.isEmpty()) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (TextUtils.isEmpty(it.next())) {
                    it.remove();
                }
            }
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                sb.append(list.get(i2));
                if (i2 < size - 1) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    public static List<String> a(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null && str.length() > 0) {
            try {
                String[] split = str.split(",");
                Iterator it = new ArrayList(Arrays.asList(split)).iterator();
                while (it.hasNext()) {
                    if (TextUtils.isEmpty((String) it.next())) {
                        it.remove();
                    }
                }
                for (String str2 : split) {
                    if (!TextUtils.isEmpty(str2) && !arrayList.contains(str2)) {
                        arrayList.add(str2);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return arrayList;
    }
}
