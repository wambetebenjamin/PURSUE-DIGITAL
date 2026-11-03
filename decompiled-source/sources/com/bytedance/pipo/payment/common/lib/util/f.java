package com.bytedance.pipo.payment.common.lib.util;

import android.util.Pair;
import com.bytedance.common.utility.l;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* compiled from: NetWorkUtils.java */
/* loaded from: classes2.dex */
public class f {
    public static String a(String str, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(new Pair(entry.getKey(), entry.getValue()));
            }
        }
        return a(str, arrayList);
    }

    private static String a(String str, List<Pair<String, String>> list) {
        StringBuilder sb = new StringBuilder(str);
        if (str.indexOf(63) < 0) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        sb.append(l.format(list, "UTF-8"));
        return sb.toString();
    }
}
