package com.bytedance.common.utility;
/* compiled from: ICustomToast.java */
/* loaded from: classes2.dex */
public interface g {
    public static final int LENGTH_LONG = 3500;
    public static final int LENGTH_SHORT = 2000;
    public static final int LENGTH_WITH_ICON = 1500;

    void dismissCustomToast();

    void showCustomLongToast(int i2, String str);

    void showCustomToast(int i2, String str);

    void showCustomToast(int i2, String str, int i3, int i4);

    void showCustomToast(String str);

    void showCustomToast(String str, int i2, int i3);
}
