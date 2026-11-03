package com.bytedance.bdinstall;

import android.content.Context;
/* compiled from: AppContext.java */
/* loaded from: classes.dex */
public interface h extends z {
    int getAid();

    String getAppName();

    String getChannel();

    Context getContext();

    long getManifestVersionCode();

    String getTweakedChannel();

    long getUpdateVersionCode();

    String getVersion();

    long getVersionCode();

    boolean isMainInstance();
}
