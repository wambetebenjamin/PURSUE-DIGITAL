package com.bytedance.ttgame.sdk.module.account.platform.api;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.bytedance.ttgame.sdk.module.account.platform.api.IThirdAuthorizeService;
import com.facebook.share.internal.ShareConstants;
/* loaded from: classes7.dex */
public class LoginPlatformUtil {
    public static String getPlatformNameByPassportPlatformName(String str) {
        return str;
    }

    /* renamed from: com.bytedance.ttgame.sdk.module.account.platform.api.LoginPlatformUtil$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform;

        static {
            int[] iArr = new int[Platform.values().length];
            $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform = iArr;
            try {
                iArr[Platform.Line.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Facebook.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Google.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Twitter.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Vk.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Tiktok.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.PASSWORD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Email.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.GooglePlay.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Mobile.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.SuccessionCode.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Steam.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Meta.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Pico.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[Platform.Pass.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public static int getUserType(Platform platform) {
        switch (AnonymousClass1.$SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[platform.ordinal()]) {
            case 1:
                return 8;
            case 2:
                return 6;
            case 3:
                return 5;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
            case 7:
                return 100;
            case 8:
                return 17;
            case 9:
                return 19;
            case 10:
                return 2;
            case 11:
                return 99;
            case 12:
                return 20;
            case 13:
                return 22;
            case 14:
                return 21;
            case 15:
                return 27;
            default:
                return 1;
        }
    }

    public static Drawable getIconIdByUserType(Context context, int i2) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.icon_user_default);
        if (i2 != 2) {
            if (i2 != 10 && i2 != 15) {
                if (i2 == 17) {
                    return context.getResources().getDrawable(R.drawable.icon_email);
                }
                if (i2 != 19 && i2 != 27) {
                    if (i2 == 100) {
                        return context.getResources().getDrawable(R.drawable.icon_password);
                    }
                    if (i2 != 5 && i2 != 6 && i2 != 7 && i2 != 8) {
                        return context.getResources().getDrawable(R.drawable.icon_visitor);
                    }
                }
            }
            int iconId = getPlatformByUserType(i2).getIconId();
            return iconId != 0 ? context.getResources().getDrawable(iconId) : drawable;
        }
        return context.getResources().getDrawable(R.drawable.icon_mobile);
    }

    public static Platform getPlatformByUserType(int i2) {
        if (i2 != 2) {
            if (i2 != 10) {
                if (i2 != 15) {
                    if (i2 != 17) {
                        if (i2 != 27) {
                            if (i2 != 5) {
                                if (i2 != 6) {
                                    if (i2 != 7) {
                                        if (i2 != 8) {
                                            if (i2 != 99) {
                                                if (i2 == 100) {
                                                    return Platform.PASSWORD;
                                                }
                                                switch (i2) {
                                                    case 19:
                                                        return Platform.GooglePlay;
                                                    case 20:
                                                        return Platform.Steam;
                                                    case 21:
                                                        return Platform.Pico;
                                                    case 22:
                                                        return Platform.Meta;
                                                    default:
                                                        return null;
                                                }
                                            }
                                            return Platform.SuccessionCode;
                                        }
                                        return Platform.Line;
                                    }
                                    return Platform.Twitter;
                                }
                                return Platform.Facebook;
                            }
                            return Platform.Google;
                        }
                        return Platform.Pass;
                    }
                    return Platform.Email;
                }
                return Platform.Tiktok;
            }
            return Platform.Vk;
        }
        return Platform.Mobile;
    }

    public static String getPlatformNameByUserType(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 5) {
                    if (i2 != 6) {
                        if (i2 != 7) {
                            if (i2 != 8) {
                                if (i2 != 10) {
                                    if (i2 != 11) {
                                        if (i2 != 15) {
                                            if (i2 != 17) {
                                                if (i2 != 27) {
                                                    if (i2 != 99) {
                                                        if (i2 == 100) {
                                                            return Platform.PASSWORD.getPlatformName();
                                                        }
                                                        switch (i2) {
                                                            case 19:
                                                                return Platform.GooglePlay.getPlatformName();
                                                            case 20:
                                                                return Platform.Steam.getPlatformName();
                                                            case 21:
                                                                return Platform.Pico.getPlatformName();
                                                            case 22:
                                                                return Platform.Meta.getPlatformName();
                                                            default:
                                                                return "unknown";
                                                        }
                                                    }
                                                    return Platform.SuccessionCode.getPlatformName();
                                                }
                                                return Platform.Pass.getPlatformName();
                                            }
                                            return Platform.Email.getPlatformName();
                                        }
                                        return Platform.Tiktok.getPlatformName();
                                    }
                                    return Platform.Apple.getPlatformName();
                                }
                                return Platform.Vk.getPlatformName();
                            }
                            return Platform.Line.getPlatformName();
                        }
                        return Platform.Twitter.getPlatformName();
                    }
                    return Platform.Facebook.getPlatformName();
                }
                return Platform.Google.getPlatformName();
            }
            return Platform.Mobile.getPlatformName();
        }
        return "guest";
    }

    public static String getThirdVariantNameByPassportPlatformName(String str) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1240244679:
                if (str.equals("google")) {
                    c2 = 0;
                    break;
                }
                break;
            case -916346253:
                if (str.equals("twitter")) {
                    c2 = 1;
                    break;
                }
                break;
            case -873713414:
                if (str.equals("tiktok")) {
                    c2 = 2;
                    break;
                }
                break;
            case -334831238:
                if (str.equals(IThirdAuthorizeService.ThirdVariant.GooglePlay)) {
                    c2 = 3;
                    break;
                }
                break;
            case 3765:
                if (str.equals("vk")) {
                    c2 = 4;
                    break;
                }
                break;
            case 3321844:
                if (str.equals("line")) {
                    c2 = 5;
                    break;
                }
                break;
            case 3347973:
                if (str.equals(IThirdAuthorizeService.ThirdVariant.Meta)) {
                    c2 = 6;
                    break;
                }
                break;
            case 3433489:
                if (str.equals(IThirdAuthorizeService.ThirdVariant.Pass)) {
                    c2 = 7;
                    break;
                }
                break;
            case 3440677:
                if (str.equals(IThirdAuthorizeService.ThirdVariant.Pico)) {
                    c2 = '\b';
                    break;
                }
                break;
            case 497130182:
                if (str.equals("facebook")) {
                    c2 = '\t';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return "google";
            case 1:
                return "twitter";
            case 2:
                return "tiktok";
            case 3:
                return IThirdAuthorizeService.ThirdVariant.GooglePlay;
            case 4:
                return "vk";
            case 5:
                return "line";
            case 6:
                return IThirdAuthorizeService.ThirdVariant.Meta;
            case 7:
                return IThirdAuthorizeService.ThirdVariant.Pass;
            case '\b':
                return IThirdAuthorizeService.ThirdVariant.Pico;
            case '\t':
                return "facebook";
            default:
                return "";
        }
    }

    public static int getIntLoginWayByStringLoginWay(String str) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1240244679:
                if (str.equals("google")) {
                    c2 = 0;
                    break;
                }
                break;
            case -916346253:
                if (str.equals("twitter")) {
                    c2 = 1;
                    break;
                }
                break;
            case -873713414:
                if (str.equals("tiktok")) {
                    c2 = 2;
                    break;
                }
                break;
            case -334831238:
                if (str.equals(IThirdAuthorizeService.ThirdVariant.GooglePlay)) {
                    c2 = 3;
                    break;
                }
                break;
            case 3765:
                if (str.equals("vk")) {
                    c2 = 4;
                    break;
                }
                break;
            case 3321844:
                if (str.equals("line")) {
                    c2 = 5;
                    break;
                }
                break;
            case 3347973:
                if (str.equals(IThirdAuthorizeService.ThirdVariant.Meta)) {
                    c2 = 6;
                    break;
                }
                break;
            case 3433489:
                if (str.equals(IThirdAuthorizeService.ThirdVariant.Pass)) {
                    c2 = 7;
                    break;
                }
                break;
            case 3440677:
                if (str.equals(IThirdAuthorizeService.ThirdVariant.Pico)) {
                    c2 = '\b';
                    break;
                }
                break;
            case 96619420:
                if (str.equals("email")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 98708952:
                if (str.equals("guest")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 107953788:
                if (str.equals(ShareConstants.WEB_DIALOG_PARAM_QUOTE)) {
                    c2 = 11;
                    break;
                }
                break;
            case 109760848:
                if (str.equals("steam")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 497130182:
                if (str.equals("facebook")) {
                    c2 = '\r';
                    break;
                }
                break;
            case 1216985755:
                if (str.equals("password")) {
                    c2 = 14;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return 5;
            case 1:
                return 7;
            case 2:
                return 15;
            case 3:
                return 19;
            case 4:
                return 10;
            case 5:
                return 8;
            case 6:
                return 22;
            case 7:
                return 27;
            case '\b':
                return 21;
            case '\t':
                return 17;
            case '\n':
                return 1;
            case 11:
                return 99;
            case '\f':
                return 20;
            case '\r':
                return 6;
            case 14:
                return 100;
            default:
                return 0;
        }
    }

    public static String getThirdVariant(Platform platform) {
        switch (AnonymousClass1.$SwitchMap$com$bytedance$ttgame$sdk$module$account$platform$api$Platform[platform.ordinal()]) {
            case 1:
                return "line";
            case 2:
                return "facebook";
            case 3:
                return "google";
            case 4:
                return "twitter";
            case 5:
                return "vk";
            case 6:
                return "tiktok";
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            default:
                return "";
            case 9:
                return IThirdAuthorizeService.ThirdVariant.GooglePlay;
            case 13:
                return IThirdAuthorizeService.ThirdVariant.Meta;
            case 14:
                return IThirdAuthorizeService.ThirdVariant.Pico;
            case 15:
                return IThirdAuthorizeService.ThirdVariant.Pass;
        }
    }
}
