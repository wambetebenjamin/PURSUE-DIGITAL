package com.android.vending.expansion.zipfile;

import android.content.Context;
import android.os.Environment;
import com.bytedance.librarian.c;
import java.io.File;
import java.util.Vector;
/* compiled from: APKExpansionSupportV2.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11a = "/Android/obb/";

    static String[] a(Context context, int i2, int i3) {
        String packageName = context.getPackageName();
        Vector vector = new Vector();
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(Environment.getExternalStorageDirectory().toString() + f11a + packageName);
            if (file.exists()) {
                if (i2 > 0) {
                    String str = file + File.separator + "main." + i2 + c.a.DOT + packageName + ".obb";
                    if (new File(str).isFile()) {
                        vector.add(str);
                    }
                }
                if (i3 > 0) {
                    String str2 = file + File.separator + "patch." + i3 + c.a.DOT + packageName + ".obb";
                    if (new File(str2).isFile()) {
                        vector.add(str2);
                    }
                }
            }
        }
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        return strArr;
    }

    public static b a(String[] strArr) {
        b bVar = null;
        for (String str : strArr) {
            if (bVar == null) {
                bVar = new b(str);
            } else {
                bVar.d(str);
            }
        }
        return bVar;
    }

    public static b b(Context context, int i2, int i3) {
        return a(a(context, i2, i3));
    }
}
