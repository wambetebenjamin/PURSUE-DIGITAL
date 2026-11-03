package com.bytedance.common.jato.memory.LeakRepair;

import android.text.TextUtils;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* compiled from: ObjectFieldsUtils.java */
/* loaded from: classes.dex */
public class e {
    private static boolean a(String str) {
        return TextUtils.isEmpty(str) || str.startsWith("android.") || str.startsWith("androidx.") || !str.startsWith("com.");
    }

    private static List<Field> b(Object obj) {
        Class<?> cls = obj.getClass();
        ArrayList arrayList = new ArrayList();
        if (a(cls.getName())) {
            return arrayList;
        }
        while (cls != null) {
            arrayList.addAll(new ArrayList(Arrays.asList(cls.getDeclaredFields())));
            cls = cls.getSuperclass();
            if (cls != null && a(cls.getName())) {
                break;
            }
        }
        return arrayList;
    }

    public static void a(Object obj) {
        for (Field field : b(obj)) {
            if (!Modifier.isStatic(field.getModifiers())) {
                Class<?> type = field.getType();
                field.setAccessible(true);
                if (View.class.isAssignableFrom(type)) {
                    try {
                        if (((View) field.get(obj)) == null) {
                            return;
                        }
                        field.set(obj, null);
                    } catch (Throwable unused) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
    }
}
