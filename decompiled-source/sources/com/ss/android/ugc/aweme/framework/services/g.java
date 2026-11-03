package com.ss.android.ugc.aweme.framework.services;

import androidx.exifinterface.media.ExifInterface;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
/* compiled from: Extention.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001H\u0086\b¢\u0006\u0002\u0010\u0002\u001a\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004\"\u0006\b\u0000\u0010\u0001\u0018\u0001H\u0086\b\u001a&\u0010\u0005\u001a\u0004\u0018\u0001H\u0006\"\n\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00060\bH\u0086\b¢\u0006\u0002\u0010\t\u001a&\u0010\u0005\u001a\u0004\u0018\u0001H\u0006\"\n\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00060\nH\u0086\b¢\u0006\u0002\u0010\u000b\u001a%\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0004\"\n\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00060\bH\u0086\b\u001a%\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0004\"\n\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00060\nH\u0086\b¨\u0006\r"}, d2 = {"spi", "SERVICE", "()Ljava/lang/Object;", "spis", "", "getService", ExifInterface.GPS_DIRECTION_TRUE, "", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "getServices", "spi_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes9.dex */
public final class g {
    public static final /* synthetic */ <SERVICE> SERVICE a() {
        i c2 = i.c();
        Intrinsics.reifiedOperationMarker(4, "SERVICE");
        return (SERVICE) c2.a(Object.class);
    }

    public static final /* synthetic */ <SERVICE> Set<SERVICE> b() {
        i c2 = i.c();
        Intrinsics.reifiedOperationMarker(4, "SERVICE");
        Set<SERVICE> c3 = c2.c(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(c3, "ServiceManager.get().get…ices(SERVICE::class.java)");
        return c3;
    }

    public static final /* synthetic */ <T> T a(KClass<T> getService) {
        Intrinsics.checkParameterIsNotNull(getService, "$this$getService");
        i c2 = i.c();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) c2.a(Object.class);
    }

    public static final /* synthetic */ <T> T a(Class<T> getService) {
        Intrinsics.checkParameterIsNotNull(getService, "$this$getService");
        i c2 = i.c();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) c2.a(Object.class);
    }

    public static final /* synthetic */ <T> Set<T> b(KClass<T> getServices) {
        Intrinsics.checkParameterIsNotNull(getServices, "$this$getServices");
        i c2 = i.c();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Set<T> c3 = c2.c(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(c3, "ServiceManager.get().getServices(T::class.java)");
        return c3;
    }

    public static final /* synthetic */ <T> Set<T> b(Class<T> getServices) {
        Intrinsics.checkParameterIsNotNull(getServices, "$this$getServices");
        i c2 = i.c();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Set<T> c3 = c2.c(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(c3, "ServiceManager.get().getServices(T::class.java)");
        return c3;
    }
}
