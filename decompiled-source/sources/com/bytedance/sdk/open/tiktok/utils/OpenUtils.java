package com.bytedance.sdk.open.tiktok.utils;

import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
/* loaded from: classes3.dex */
public class OpenUtils {
    private static boolean visibilityValid(int i2) {
        return i2 == 0 || i2 == 8 || i2 == 4;
    }

    public static String[] arrayUnique(String[] strArr) {
        if (strArr != null) {
            try {
                if (strArr.length > 0) {
                    return (String[]) new HashSet(new ArrayList(Arrays.asList(strArr))).toArray(new String[0]);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return strArr;
    }

    public static void setViewVisibility(View view, int i2) {
        if (view == null || view.getVisibility() == i2 || !visibilityValid(i2)) {
            return;
        }
        view.setVisibility(i2);
    }
}
