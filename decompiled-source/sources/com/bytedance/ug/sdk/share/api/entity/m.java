package com.bytedance.ug.sdk.share.api.entity;

import com.bytedance.ttgame.module.share.api.entity.TTShareStrategy;
/* compiled from: ShareStrategy.java */
/* loaded from: classes8.dex */
public enum m {
    NORMAL,
    SHARE_WITH_COMPONENT,
    SHARE_WITH_TOKEN,
    SHARE_WITH_COMPONET_OPTIMIZE,
    SHARE_WITH_IMAGE_TOKEN,
    SHARE_WITH_VIDEO;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static m getStrategyByType(String str) {
        char c2;
        switch (str.hashCode()) {
            case -1737986495:
                if (str.equals(TTShareStrategy.SHARE_WITH_COMPONET_OPTIMIZE)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 113722:
                if (str.equals("sdk")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 114381:
                if (str.equals("sys")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 100313435:
                if (str.equals("image")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 110541305:
                if (str.equals("token")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 112202875:
                if (str.equals("video")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 != 0) {
            if (c2 != 1) {
                if (c2 != 2) {
                    if (c2 != 3) {
                        if (c2 == 4) {
                            return SHARE_WITH_VIDEO;
                        }
                        return NORMAL;
                    }
                    return SHARE_WITH_IMAGE_TOKEN;
                }
                return SHARE_WITH_COMPONET_OPTIMIZE;
            }
            return SHARE_WITH_TOKEN;
        }
        return SHARE_WITH_COMPONENT;
    }

    /* compiled from: ShareStrategy.java */
    /* renamed from: com.bytedance.ug.sdk.share.api.entity.m$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f15595a;

        static {
            int[] iArr = new int[m.values().length];
            f15595a = iArr;
            try {
                iArr[m.SHARE_WITH_COMPONENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15595a[m.SHARE_WITH_TOKEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15595a[m.SHARE_WITH_COMPONET_OPTIMIZE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15595a[m.SHARE_WITH_IMAGE_TOKEN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15595a[m.SHARE_WITH_VIDEO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15595a[m.NORMAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static String getStrategyStr(m mVar) {
        if (mVar == null) {
            return "";
        }
        int i2 = AnonymousClass1.f15595a[mVar.ordinal()];
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "sdk" : "video" : "image" : TTShareStrategy.SHARE_WITH_COMPONET_OPTIMIZE : "token" : "sys";
    }
}
