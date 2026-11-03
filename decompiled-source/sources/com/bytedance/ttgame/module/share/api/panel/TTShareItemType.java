package com.bytedance.ttgame.module.share.api.panel;

import android.text.TextUtils;
import com.bytedance.ttgame.module.share.api.TTPanelItemType;
/* loaded from: classes7.dex */
public enum TTShareItemType implements TTPanelItemType {
    WX_TIMELINE(4),
    WX(3),
    QQ(2),
    QZONE(1),
    SYSTEM(15),
    DOUYIN(6),
    DOUYIN_IM(16),
    WEIBO(5),
    WEIBO_SG(19),
    TAPTAP(20),
    FACEBOOK(7),
    LINE(11),
    WHATSAPP(10),
    INSTAGRAM(9),
    TIKTOK(13),
    TWITTER(14),
    MESSENGER(8),
    IMAGE_SHARE(18),
    VK(17);
    
    private int value;

    TTShareItemType(int i2) {
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }

    public static TTShareItemType getShareItemType(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1777292293:
                if (str.equals("image_share")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1436108013:
                if (str.equals("messenger")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1325936172:
                if (str.equals("douyin")) {
                    c2 = 2;
                    break;
                }
                break;
            case -916346253:
                if (str.equals("twitter")) {
                    c2 = 3;
                    break;
                }
                break;
            case -880953056:
                if (str.equals("taptap")) {
                    c2 = 4;
                    break;
                }
                break;
            case -873713414:
                if (str.equals("tiktok")) {
                    c2 = 5;
                    break;
                }
                break;
            case -791770330:
                if (str.equals("wechat")) {
                    c2 = 6;
                    break;
                }
                break;
            case -515441397:
                if (str.equals("weibo_sg")) {
                    c2 = 7;
                    break;
                }
                break;
            case -150184081:
                if (str.equals("douyin_im")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 3616:
                if (str.equals("qq")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 3765:
                if (str.equals("vk")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 3321844:
                if (str.equals("line")) {
                    c2 = 11;
                    break;
                }
                break;
            case 28903346:
                if (str.equals("instagram")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 108102557:
                if (str.equals("qzone")) {
                    c2 = '\r';
                    break;
                }
                break;
            case 113011944:
                if (str.equals("weibo")) {
                    c2 = 14;
                    break;
                }
                break;
            case 497130182:
                if (str.equals("facebook")) {
                    c2 = 15;
                    break;
                }
                break;
            case 540697581:
                if (str.equals("sys_share")) {
                    c2 = 16;
                    break;
                }
                break;
            case 1235271283:
                if (str.equals("moments")) {
                    c2 = 17;
                    break;
                }
                break;
            case 1934780818:
                if (str.equals("whatsapp")) {
                    c2 = 18;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return IMAGE_SHARE;
            case 1:
                return MESSENGER;
            case 2:
                return DOUYIN;
            case 3:
                return TWITTER;
            case 4:
                return TAPTAP;
            case 5:
                return TIKTOK;
            case 6:
                return WX;
            case 7:
                return WEIBO_SG;
            case '\b':
                return DOUYIN_IM;
            case '\t':
                return QQ;
            case '\n':
                return VK;
            case 11:
                return LINE;
            case '\f':
                return INSTAGRAM;
            case '\r':
                return QZONE;
            case 14:
                return WEIBO;
            case 15:
                return FACEBOOK;
            case 16:
                return SYSTEM;
            case 17:
                return WX_TIMELINE;
            case 18:
                return WHATSAPP;
            default:
                return null;
        }
    }

    /* renamed from: com.bytedance.ttgame.module.share.api.panel.TTShareItemType$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType;

        static {
            int[] iArr = new int[TTShareItemType.values().length];
            $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType = iArr;
            try {
                iArr[TTShareItemType.WX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.WX_TIMELINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.QQ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.QZONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.SYSTEM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.DOUYIN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.DOUYIN_IM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.WEIBO.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.WEIBO_SG.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.FACEBOOK.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.LINE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.WHATSAPP.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.INSTAGRAM.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.TIKTOK.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.TWITTER.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.MESSENGER.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.IMAGE_SHARE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.VK.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[TTShareItemType.TAPTAP.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    public static String getChannel(TTShareItemType tTShareItemType) {
        if (tTShareItemType == null) {
            return null;
        }
        switch (AnonymousClass1.$SwitchMap$com$bytedance$ttgame$module$share$api$panel$TTShareItemType[tTShareItemType.ordinal()]) {
            case 1:
                return "wechat";
            case 2:
                return "moments";
            case 3:
                return "qq";
            case 4:
                return "qzone";
            case 5:
                return "sys_share";
            case 6:
                return "douyin";
            case 7:
                return "douyin_im";
            case 8:
                return "weibo";
            case 9:
                return "weibo_sg";
            case 10:
                return "facebook";
            case 11:
                return "line";
            case 12:
                return "whatsapp";
            case 13:
                return "instagram";
            case 14:
                return "tiktok";
            case 15:
                return "twitter";
            case 16:
                return "messenger";
            case 17:
                return "image_share";
            case 18:
                return "vk";
            case 19:
                return "taptap";
            default:
                return null;
        }
    }
}
