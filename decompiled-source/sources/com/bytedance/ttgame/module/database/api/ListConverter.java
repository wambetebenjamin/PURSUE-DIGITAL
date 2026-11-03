package com.bytedance.ttgame.module.database.api;

import android.text.TextUtils;
import com.bytedance.ttgame.sdk.module.utils.NumberUtils;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;
/* loaded from: classes5.dex */
public class ListConverter {
    public static List<Integer> stringToIntegerList(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(",")) {
            try {
                arrayList.add(Integer.valueOf(NumberUtils.getInteger(str2, -1)));
            } catch (Exception e2) {
                Timber.tag("gsdk_converter").d(e2);
            }
        }
        return arrayList;
    }

    public static String integerListToString(List<Integer> list) {
        if (list == null || list.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 == list.size() - 1) {
                sb.append(list.get(i2));
            } else {
                sb.append(list.get(i2)).append(",");
            }
        }
        return sb.toString();
    }

    public static List<String> stringToStringList(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(",")) {
            try {
                arrayList.add(str2);
            } catch (Exception e2) {
                Timber.tag("gsdk_converter").d(e2);
            }
        }
        return arrayList;
    }

    public static String stringListToString(List<String> list) {
        if (list == null || list.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 == list.size() - 1) {
                sb.append(list.get(i2));
            } else {
                sb.append(list.get(i2)).append(",");
            }
        }
        return sb.toString();
    }
}
