package com.bytedance.ttgame.rocketapi.account;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes7.dex */
public class PhoneType {
    public static final int LoginOrBind = 1;
    public static final int SetPassword = 3;
    public static final int Unbind = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface phoneType {
    }
}
