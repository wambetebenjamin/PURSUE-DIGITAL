package com.bytedance.ttgame.library.module_manager.internal;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.ttgame.framework.module.spi.IService;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ResourceServiceLoader.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/ResourceServiceLoader;", "", "()V", "Companion", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ResourceServiceLoader {
    public static final Companion Companion = new Companion(null);

    /* compiled from: ResourceServiceLoader.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\"\b\b\u0000\u0010\u0006*\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\b¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/ResourceServiceLoader$Companion;", "", "()V", "loadService", "", "Lcom/bytedance/ttgame/framework/module/spi/IService;", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Ljava/lang/Class;", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T extends IService> Set<IService> loadService(Class<T> clazz) {
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            Logger.INSTANCE.logVerbose("正在通过 resource 装载 service: " + clazz.getName());
            Iterator it = ServiceLoader.load(clazz).iterator();
            Intrinsics.checkNotNullExpressionValue(it, "jdkServiceLoader.iterator()");
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (it.hasNext()) {
                Object next = it.next();
                Intrinsics.checkNotNullExpressionValue(next, "loadIterator.next()");
                linkedHashSet.add(next);
            }
            if (linkedHashSet.isEmpty()) {
                Logger.INSTANCE.logError("通过 resources 没有发现服务: " + clazz.getName() + (char) 65281);
            } else {
                Logger.INSTANCE.logSuccess("通过 resources 装载服务: " + clazz.getName() + " 成功！");
                Logger.INSTANCE.logVerbose("发现 service 的实现列表：");
                Iterator it2 = linkedHashSet.iterator();
                while (it2.hasNext()) {
                    Logger.INSTANCE.logVerbose("\t\t" + ((IService) it2.next()).getClass().getCanonicalName());
                }
            }
            if (linkedHashSet.isEmpty()) {
                return null;
            }
            return linkedHashSet;
        }
    }
}
