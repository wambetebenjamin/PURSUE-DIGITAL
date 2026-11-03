package com.bytedance.push.configuration;

import android.text.TextUtils;
/* compiled from: NotificationChannel.java */
/* loaded from: classes2.dex */
public class d {
    public final String id;
    public final String name;

    public d(String str, String str2) {
        this.name = str;
        this.id = str2;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(this.id) || TextUtils.isEmpty(this.name)) ? false : true;
    }
}
