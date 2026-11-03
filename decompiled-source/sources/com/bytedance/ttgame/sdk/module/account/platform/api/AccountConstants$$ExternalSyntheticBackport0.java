package com.bytedance.ttgame.sdk.module.account.platform.api;
/* compiled from: D8$$SyntheticClass */
/* loaded from: classes7.dex */
public final /* synthetic */ class AccountConstants$$ExternalSyntheticBackport0 {
    public static /* synthetic */ boolean m(String str) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = str.codePointAt(i2);
            if (!Character.isWhitespace(codePointAt)) {
                return false;
            }
            i2 += Character.charCount(codePointAt);
        }
        return true;
    }
}
