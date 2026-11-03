package com.appsflyer.internal;

import android.content.Intent;
import com.appsflyer.AFLogger;
import java.util.ConcurrentModificationException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
/* loaded from: classes.dex */
public final class AFi1jSDK {
    final Intent AFKeystoreWrapper;

    public AFi1jSDK(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "");
        this.AFKeystoreWrapper = intent;
    }

    public final String AFKeystoreWrapper(final String str) {
        Intrinsics.checkNotNullParameter(str, "");
        return (String) AFInAppEventParameterName(new Function0<String>() { // from class: com.appsflyer.internal.AFi1jSDK.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFInAppEventType */
            public final String invoke() {
                return AFi1jSDK.this.AFKeystoreWrapper.getStringExtra(str);
            }
        }, new StringBuilder("Error while trying to read ").append(str).append(" extra from intent").toString(), null, true);
    }

    public final boolean valueOf(final String str) {
        Intrinsics.checkNotNullParameter(str, "");
        Boolean bool = (Boolean) AFInAppEventParameterName(new Function0<Boolean>() { // from class: com.appsflyer.internal.AFi1jSDK.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFKeystoreWrapper */
            public final Boolean invoke() {
                return Boolean.valueOf(AFi1jSDK.this.AFKeystoreWrapper.hasExtra(str));
            }
        }, new StringBuilder("Error while trying to check presence of ").append(str).append(" extra from intent").toString(), Boolean.TRUE, true);
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public final Intent AFInAppEventParameterName(final String str, final long j2) {
        Intrinsics.checkNotNullParameter(str, "");
        return (Intent) AFInAppEventParameterName(new Function0<Intent>() { // from class: com.appsflyer.internal.AFi1jSDK.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: valueOf */
            public final Intent invoke() {
                return AFi1jSDK.this.AFKeystoreWrapper.putExtra(str, j2);
            }
        }, new StringBuilder("Error while trying to write ").append(str).append(" extra to intent").toString(), null, true);
    }

    public final <T> T AFInAppEventParameterName(Function0<? extends T> function0, String str, T t, boolean z) {
        Object m868constructorimpl;
        Object obj;
        synchronized (this.AFKeystoreWrapper) {
            try {
                Result.Companion companion = Result.Companion;
                AFi1jSDK aFi1jSDK = this;
                m868constructorimpl = Result.m868constructorimpl(function0.invoke());
                KClass[] kClassArr = {Reflection.getOrCreateKotlinClass(ConcurrentModificationException.class), Reflection.getOrCreateKotlinClass(ArrayIndexOutOfBoundsException.class)};
                Throwable m871exceptionOrNullimpl = Result.m871exceptionOrNullimpl(m868constructorimpl);
                if (m871exceptionOrNullimpl != null) {
                    Result.Companion companion2 = Result.Companion;
                    if (ArraysKt.contains(kClassArr, Reflection.getOrCreateKotlinClass(m871exceptionOrNullimpl.getClass()))) {
                        if (z) {
                            obj = AFInAppEventParameterName(function0, str, t, false);
                        } else {
                            AFLogger.afErrorLog(str, m871exceptionOrNullimpl, false, false);
                            obj = t;
                        }
                        m868constructorimpl = (T) Result.m868constructorimpl(obj);
                    } else {
                        throw m871exceptionOrNullimpl;
                    }
                }
                KClass[] kClassArr2 = {Reflection.getOrCreateKotlinClass(RuntimeException.class)};
                Throwable m871exceptionOrNullimpl2 = Result.m871exceptionOrNullimpl(m868constructorimpl);
                if (m871exceptionOrNullimpl2 != null) {
                    Result.Companion companion3 = Result.Companion;
                    if (ArraysKt.contains(kClassArr2, Reflection.getOrCreateKotlinClass(m871exceptionOrNullimpl2.getClass()))) {
                        AFLogger.afErrorLog(str, m871exceptionOrNullimpl2, false, false);
                        m868constructorimpl = (T) Result.m868constructorimpl(t);
                    } else {
                        throw m871exceptionOrNullimpl2;
                    }
                }
                ResultKt.throwOnFailure(m868constructorimpl);
            }
        }
        return (T) m868constructorimpl;
    }
}
