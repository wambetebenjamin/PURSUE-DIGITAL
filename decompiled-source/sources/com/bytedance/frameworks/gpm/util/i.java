package com.bytedance.frameworks.gpm.util;

import com.bytedance.frameworks.gpm.GPMMonitor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
/* compiled from: ThreadUtil.java */
/* loaded from: classes2.dex */
public class i {
    public static int getThreadCount() {
        return a().size();
    }

    public static String getTIDs() {
        JSONArray jSONArray = new JSONArray();
        for (Integer num : a()) {
            jSONArray.put(num.intValue());
        }
        return jSONArray.toString();
    }

    private static List<Integer> a() {
        String[] list;
        ArrayList arrayList = new ArrayList();
        try {
            list = new File("/proc/self/task/").list();
        } catch (Throwable th) {
            GPMMonitor.loge("thread id:" + th.toString());
        }
        if (list == null) {
            return arrayList;
        }
        new JSONArray();
        for (String str : list) {
            try {
                arrayList.add(Integer.valueOf(Integer.parseInt(str)));
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }
}
