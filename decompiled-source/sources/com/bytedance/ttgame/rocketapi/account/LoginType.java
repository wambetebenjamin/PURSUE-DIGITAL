package com.bytedance.ttgame.rocketapi.account;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes7.dex */
public class LoginType {
    public static final int AOLIGAME = 12;
    public static final int AWEME = 4;
    public static final int EMAIL = 17;
    public static final int FACEBOOK = 6;
    public static final int GOOGLE = 5;
    public static final int GOOGLEPLAY = 19;
    public static final int LINE = 8;
    public static final int META = 22;
    public static final int MOBILE = 2;
    public static final int PASS = 27;
    public static final int PICO = 21;
    public static final int QQ = 11;
    public static final int STEAM = 20;
    public static final int TAPTAP = 16;
    public static final int TIKTOK = 15;
    public static final int TOUTIAO = 3;
    public static final int TWITTER = 7;
    public static final int VISITOR = 1;
    public static final int VK = 10;
    public static final int WX = 12;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface loginType {
    }
}
