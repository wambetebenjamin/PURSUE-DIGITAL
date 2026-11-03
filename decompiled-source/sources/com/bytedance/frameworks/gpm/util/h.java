package com.bytedance.frameworks.gpm.util;

import android.os.Environment;
import android.os.StatFs;
/* compiled from: ROMUtil.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static g f8568a = new g();

    public static int getROMSize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return (int) ((statFs.getBlockSize() * statFs.getBlockCount()) / 1024);
    }

    public static int getROMSizeAvail() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return (int) ((statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1024);
    }

    String a() {
        return f8568a.a();
    }
}
