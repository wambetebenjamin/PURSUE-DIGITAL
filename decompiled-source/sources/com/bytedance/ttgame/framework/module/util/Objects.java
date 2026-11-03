package com.bytedance.ttgame.framework.module.util;

import java.util.Arrays;
/* loaded from: classes3.dex */
public class Objects {
    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else if (obj2 == null) {
            return false;
        } else {
            return obj.equals(obj2);
        }
    }

    public static int hash(Object... objArr) {
        return Arrays.hashCode(objArr);
    }
}
