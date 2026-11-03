package com.ss.android.ugc.aweme.framework.services.utils;

import com.ss.android.ugc.aweme.framework.services.annotation.c;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: KotlinReflectHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"reflectObjectClass", "Lcom/ss/android/ugc/aweme/framework/services/annotation/IAabDelegate;", "", "spi_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes9.dex */
public final class a {
    public static final c a(String reflectObjectClass) {
        Intrinsics.checkParameterIsNotNull(reflectObjectClass, "$this$reflectObjectClass");
        try {
            Class<?> cls = Class.forName(reflectObjectClass);
            if (cls != null) {
                Field declaredField = cls.getDeclaredField("INSTANCE");
                Intrinsics.checkExpressionValueIsNotNull(declaredField, "it.getDeclaredField(\"INSTANCE\")");
                Object obj = declaredField.get(null);
                if (!(obj instanceof c)) {
                    obj = null;
                }
                return (c) obj;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
