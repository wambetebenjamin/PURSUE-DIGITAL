package com.bytedance.ttgame.rocketapi.account;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes7.dex */
public class AccountEmailCodeType {
    public static final int Bind = 2;
    public static final int Register = 1;
    public static final int SetPassword = 4;
    public static final int Unbind = 3;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface emailCodeType {
    }
}
