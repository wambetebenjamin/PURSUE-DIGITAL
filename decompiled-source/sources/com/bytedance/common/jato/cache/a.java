package com.bytedance.common.jato.cache;

import android.content.res.Resources;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
/* compiled from: JatoOptXmlCache.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4643a = 4;

    public static void a(Resources resources, int i2) {
        Field declaredField;
        try {
            Object a2 = a(resources, "mResourcesImpl");
            if (a2 == null || (declaredField = a2.getClass().getDeclaredField("mCachedXmlBlocks")) == null) {
                return;
            }
            declaredField.setAccessible(true);
            synchronized (declaredField.get(a2)) {
                Field declaredField2 = a2.getClass().getDeclaredField("mCachedXmlBlockFiles");
                if (declaredField2 == null) {
                    return;
                }
                declaredField2.setAccessible(true);
                String[] strArr = (String[]) declaredField2.get(a2);
                if (strArr.length == 4) {
                    String[] strArr2 = new String[i2];
                    Field declaredField3 = a2.getClass().getDeclaredField("mCachedXmlBlockCookies");
                    declaredField3.setAccessible(true);
                    int[] iArr = new int[i2];
                    int[] iArr2 = (int[]) declaredField3.get(a2);
                    Object[] objArr = (Object[]) Array.newInstance(declaredField.getType().getComponentType(), i2);
                    Object[] objArr2 = (Object[]) declaredField.get(a2);
                    for (int i3 = 0; i3 < 4; i3++) {
                        strArr2[i3] = strArr[i3];
                        iArr[i3] = iArr2[i3];
                        objArr[i3] = objArr2[i3];
                    }
                    declaredField2.set(a2, strArr2);
                    declaredField3.set(a2, iArr);
                    declaredField.set(a2, objArr);
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    private static <T> T a(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return (T) declaredField.get(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
