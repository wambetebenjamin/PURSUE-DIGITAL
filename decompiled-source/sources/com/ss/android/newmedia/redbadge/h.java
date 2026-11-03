package com.ss.android.newmedia.redbadge;

import android.content.ComponentName;
import android.content.Context;
/* compiled from: CnHomeBadger.java */
/* loaded from: classes9.dex */
public abstract class h implements a {

    /* renamed from: a  reason: collision with root package name */
    private final String f18397a = "CnHomeBadger";

    public abstract int a(Context context, ComponentName componentName);

    public boolean a() {
        return true;
    }

    public boolean a(int i2) {
        return true;
    }

    public boolean b(int i2) {
        return true;
    }

    public boolean a(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            com.bytedance.push.utils.h.d("CnHomeBadger", "args is null");
            return false;
        } else if (i2 <= 0) {
            com.bytedance.push.utils.h.d("CnHomeBadger", "count must bigger than 0");
            return false;
        } else {
            try {
                int a2 = a(context, componentName);
                if (a2 >= 0) {
                    executeBadge(context, componentName, a2 + i2);
                    return true;
                }
            } catch (d e2) {
                e2.printStackTrace();
            }
            return false;
        }
    }

    public boolean b(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            com.bytedance.push.utils.h.d("CnHomeBadger", "args is null");
            return false;
        } else if (i2 <= 0) {
            com.bytedance.push.utils.h.d("CnHomeBadger", "count must bigger than 0");
            return false;
        } else {
            try {
                int a2 = a(context, componentName);
                int i3 = a2 - i2;
                if (i3 < 0) {
                    com.bytedance.push.utils.h.d("CnHomeBadger", "cur badge number is " + a2 + " but try to reduce " + i2 + ", can't do it");
                    return false;
                }
                executeBadge(context, componentName, i3);
                return true;
            } catch (d e2) {
                e2.printStackTrace();
                return false;
            }
        }
    }
}
