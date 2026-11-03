package com.bytedance.ug.sdk.share.impl.ui.panel;

import com.bytedance.share_ui.R;
/* compiled from: GeneralResourceIcon.java */
/* loaded from: classes8.dex */
public class a {
    public static int getResourceIcon(com.bytedance.ug.sdk.share.api.panel.d dVar) {
        com.bytedance.ug.sdk.share.impl.share.api.b channel = com.bytedance.ug.sdk.share.impl.manager.c.getChannel(dVar);
        if (channel != null) {
            return channel.getChannelIcon();
        }
        int i2 = AnonymousClass1.f15980a[dVar.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return 0;
                    }
                    return R.drawable.share_sdk_share_icon_email;
                }
                return R.drawable.share_sdk_share_icon_sms;
            }
            return R.drawable.share_sdk_share_icon_system;
        }
        return R.drawable.share_sdk_share_icon_copylink;
    }

    /* compiled from: GeneralResourceIcon.java */
    /* renamed from: com.bytedance.ug.sdk.share.impl.ui.panel.a$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f15980a;

        static {
            int[] iArr = new int[com.bytedance.ug.sdk.share.api.panel.d.values().length];
            f15980a = iArr;
            try {
                iArr[com.bytedance.ug.sdk.share.api.panel.d.COPY_LINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15980a[com.bytedance.ug.sdk.share.api.panel.d.SYSTEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15980a[com.bytedance.ug.sdk.share.api.panel.d.SMS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15980a[com.bytedance.ug.sdk.share.api.panel.d.EMAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static String getResourceIconText(com.bytedance.ug.sdk.share.api.panel.d dVar) {
        com.bytedance.ug.sdk.share.impl.share.api.b channel = com.bytedance.ug.sdk.share.impl.manager.c.getChannel(dVar);
        if (channel != null) {
            return channel.getChannelName();
        }
        int i2 = AnonymousClass1.f15980a[dVar.ordinal()];
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "Email" : "短信" : "系统分享" : "复制链接";
    }
}
