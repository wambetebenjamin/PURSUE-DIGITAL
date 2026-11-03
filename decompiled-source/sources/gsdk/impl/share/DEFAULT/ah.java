package gsdk.impl.share.DEFAULT;

import com.bytedance.ttgame.module.share.api.panel.TTShareItemType;
/* loaded from: classes10.dex */
public class ah {

    /* renamed from: gsdk.impl.share.DEFAULT.ah$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f20736a;

        static {
            int[] iArr = new int[com.bytedance.ug.sdk.share.api.panel.d.values().length];
            f20736a = iArr;
            try {
                iArr[com.bytedance.ug.sdk.share.api.panel.d.WX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.WX_TIMELINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.QQ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.QZONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.SYSTEM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.DOUYIN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.DOUYIN_IM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.WEIBO.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.FACEBOOK.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.LINE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.WHATSAPP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.INSTAGRAM.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.TIKTOK.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.TWITTER.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.IMAGE_SHARE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.MESSENGER.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f20736a[com.bytedance.ug.sdk.share.api.panel.d.VK.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public static TTShareItemType a(com.bytedance.ug.sdk.share.api.panel.d dVar) {
        if (dVar == null) {
            return null;
        }
        switch (AnonymousClass1.f20736a[dVar.ordinal()]) {
            case 1:
                return TTShareItemType.WX;
            case 2:
                return TTShareItemType.WX_TIMELINE;
            case 3:
                return TTShareItemType.QQ;
            case 4:
                return TTShareItemType.QZONE;
            case 5:
                return TTShareItemType.SYSTEM;
            case 6:
                return TTShareItemType.DOUYIN;
            case 7:
                return TTShareItemType.DOUYIN_IM;
            case 8:
                return TTShareItemType.WEIBO;
            case 9:
                return TTShareItemType.FACEBOOK;
            case 10:
                return TTShareItemType.LINE;
            case 11:
                return TTShareItemType.WHATSAPP;
            case 12:
                return TTShareItemType.INSTAGRAM;
            case 13:
                return TTShareItemType.TIKTOK;
            case 14:
                return TTShareItemType.TWITTER;
            case 15:
                return TTShareItemType.IMAGE_SHARE;
            case 16:
                return TTShareItemType.MESSENGER;
            case 17:
                return TTShareItemType.VK;
            default:
                return null;
        }
    }
}
