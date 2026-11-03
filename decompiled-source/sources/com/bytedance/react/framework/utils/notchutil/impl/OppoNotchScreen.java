package com.bytedance.react.framework.utils.notchutil.impl;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.Window;
import com.bytedance.react.framework.utils.notchutil.INotchScreen;
import com.bytedance.react.framework.utils.notchutil.utils.ScreenUtil;
import com.facebook.internal.security.CertificateUtil;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class OppoNotchScreen implements INotchScreen {
    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean isShowInNotch(Window window) {
        return true;
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    @Deprecated
    public void setDisplayInNotch(Window window) {
    }

    private static String getScreenValue() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls.newInstance(), "ro.oppo.screen.heteromorphism");
        } catch (Throwable unused) {
            return "";
        }
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean hasNotch(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public List<Rect> getNotchRect(Context context, Window window) {
        int intValue;
        int i2;
        int i3;
        int i4;
        try {
            String screenValue = getScreenValue();
            if (!TextUtils.isEmpty(screenValue)) {
                String[] split = screenValue.split(CertificateUtil.DELIMITER);
                String[] split2 = split[0].split(",");
                String[] split3 = split[1].split(",");
                if (ScreenUtil.isPortrait(context)) {
                    i4 = Integer.valueOf(split2[0]).intValue();
                    i2 = Integer.valueOf(split2[1]).intValue();
                    i3 = Integer.valueOf(split3[0]).intValue();
                    intValue = Integer.valueOf(split3[1]).intValue();
                } else {
                    int rotation = ScreenUtil.getRotation(context);
                    int intValue2 = Integer.valueOf(split2[1]).intValue();
                    int intValue3 = Integer.valueOf(split2[0]).intValue();
                    int intValue4 = Integer.valueOf(split3[1]).intValue();
                    intValue = Integer.valueOf(split3[0]).intValue();
                    if (rotation == 3) {
                        i3 = ScreenUtil.getScreenSize(context)[0];
                        i4 = i3 - (intValue4 - intValue2);
                        i2 = intValue3;
                    } else {
                        i2 = intValue3;
                        i3 = intValue4;
                        i4 = intValue2;
                    }
                }
                Rect rect = new Rect(i4, i2, i3, intValue);
                ArrayList arrayList = new ArrayList();
                arrayList.add(rect);
                return arrayList;
            }
        } catch (Throwable unused) {
        }
        return null;
    }
}
