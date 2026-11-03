package com.bytedance.news.common.service.manager.ext;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.news.common.service.manager.IService;
import com.bytedance.news.common.service.manager.e;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
/* compiled from: ServiceManagerExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004¢\u0006\u0002\u0010\u0005\u001a!\u0010\u0006\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"getService", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/bytedance/news/common/service/manager/IService;", "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lcom/bytedance/news/common/service/manager/IService;", "impl", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)Lcom/bytedance/news/common/service/manager/IService;", "ext_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes2.dex */
public final class b {
    public static final <T extends IService> T getService(Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        return (T) e.getService(clazz);
    }

    public static final <T extends IService> T impl(KClass<T> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return (T) e.getService(JvmClassMappingKt.getJavaClass((KClass) receiver$0));
    }
}
