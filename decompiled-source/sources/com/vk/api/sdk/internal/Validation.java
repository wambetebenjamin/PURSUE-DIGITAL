package com.vk.api.sdk.internal;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.bdturing.setting.g;
import com.bytedance.sdk.account.platform.base.i;
import com.vk.api.sdk.exceptions.VKApiCodes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Validation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0010\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u000f\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006¨\u0006\u0011"}, d2 = {"Lcom/vk/api/sdk/internal/Validation;", "", "()V", "assertAccessTokenValid", "", i.CT_KEY_ACCESS_TOKEN, "", "assertCallsPerSecondLimitValid", "limit", "", "assertContextValid", "context", "Landroid/content/Context;", "assertHttpHostValid", g.HOST_KEY, "assertLangValid", VKApiCodes.PARAM_LANG, "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class Validation {
    public static final Validation INSTANCE = new Validation();

    private Validation() {
    }

    public final void assertContextValid(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
    }

    public final void assertCallsPerSecondLimitValid(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("Illegal callsPerSecondLimit value: " + i2);
        }
    }

    public final void assertHttpHostValid(String str) {
        if (str == null || str.length() == 0) {
            StringBuilder append = new StringBuilder().append("Illegal host value: ");
            if (str == null) {
                Intrinsics.throwNpe();
            }
            throw new IllegalArgumentException(append.append(str).toString());
        }
    }

    public final void assertLangValid(String str) {
        if (TextUtils.isEmpty(str)) {
            StringBuilder append = new StringBuilder().append("Illegal lang value: ");
            if (str == null) {
                Intrinsics.throwNpe();
            }
            throw new IllegalArgumentException(append.append(str).toString());
        }
    }

    public final void assertAccessTokenValid(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Illegal accessToken value");
        }
    }
}
