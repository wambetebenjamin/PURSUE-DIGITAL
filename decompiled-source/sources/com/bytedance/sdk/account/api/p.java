package com.bytedance.sdk.account.api;
/* compiled from: IEmailSendCodeType.java */
/* loaded from: classes2.dex */
public interface p {
    public static final int ECT_BIND_EMAIL = 8;
    public static final int ECT_CHANGE_EMAIL = 7;
    public static final int ECT_CHANGE_EMAIL_VERIFY = 6;
    public static final int ECT_CODE_LOGIN = 13;
    public static final int ECT_FORGET_PASSWORD = 4;
    @Deprecated
    public static final int ECT_FORGET_PASSWORD_RETRY = 5;
    public static final int ECT_MODIFY_PASSWORD = 11;
    public static final int ECT_REGISTER = 1;
    @Deprecated
    public static final int ECT_REGISTER_RETRY = 2;
    public static final int ECT_UNKNOWN = 0;

    /* compiled from: IEmailSendCodeType.java */
    /* loaded from: classes2.dex */
    public interface a {
        public static final int EMAIL_LOGIC_DEFAULT = 0;
        public static final int EMAIL_LOGIC_REGISTER_CHECK_CODE_PWD_LST = 3;
        public static final int EMAIL_LOGIC_REGISTER_PASSWORD_AFTER = 2;
        public static final int EMAIL_LOGIC_REGISTER_PASSWORD_FIRST = 1;
    }
}
