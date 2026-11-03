package com.bytedance.ttgame.channel.account;
/* loaded from: classes3.dex */
public class ChannelLoginErrorCode {
    public static final int BANNED = -1011;
    public static final int CAN_NOT_AUTO_LOGIN_CODE = -16000;
    public static final int GSDK_REALNAME_CANCEL = -475001;
    public static final int NOT_LOGING = -109800;
    public static final int PASS_NO_SUPPORT_UNBIND = -105051;
    public static final int REGISTRATION_RESTRICTIONS = -1016;
    public static final int SECURITY_ERROR_CODE = -4001;
    public static final int SUCCESS = 0;

    public static boolean isFailNeedDialog(int i2) {
        return i2 == -1016 || i2 == -1011;
    }
}
