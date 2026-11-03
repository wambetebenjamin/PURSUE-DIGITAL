package com.bytedance.ttgame.library.module_manager.internal;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.bytedance.ttgame.framework.module.spi.IService;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AssetServiceLoader.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/AssetServiceLoader;", "", "()V", "Companion", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetServiceLoader {
    public static final Companion Companion = new Companion(null);

    /* compiled from: AssetServiceLoader.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\"\b\b\u0000\u0010\u0006*\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/AssetServiceLoader$Companion;", "", "()V", "loadService", "", "Lcom/bytedance/ttgame/framework/module/spi/IService;", ExifInterface.GPS_DIRECTION_TRUE, "context", "Landroid/content/Context;", "clazz", "Ljava/lang/Class;", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T extends IService> Set<IService> loadService(Context context, Class<T> clazz) {
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            Logger.INSTANCE.logVerbose("正在通过 asset 装载 service: " + clazz.getName());
            if (context == null) {
                Logger.INSTANCE.logWarning("无法通过 asset 进行 service " + clazz.getName() + " 装载，因为 context 为空。");
                return null;
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            try {
                String[] list = context.getAssets().list("g/services/" + clazz.getName());
                if (list != null) {
                    for (String str : list) {
                        Object newInstance = Class.forName(str).newInstance();
                        if (newInstance == null) {
                            throw new NullPointerException("null cannot be cast to non-null type com.bytedance.ttgame.framework.module.spi.IService");
                        }
                        linkedHashSet.add((IService) newInstance);
                    }
                }
            } catch (Exception e2) {
                if (!(e2 instanceof IOException) && !(e2 instanceof ClassNotFoundException)) {
                    throw e2;
                }
            }
            if (linkedHashSet.isEmpty()) {
                Logger.INSTANCE.logError("通过 assets 没有发现服务: " + clazz.getName() + (char) 65281);
            } else {
                Logger.INSTANCE.logSuccess("通过 assets 装载服务: " + clazz.getName() + " 成功！");
                Logger.INSTANCE.logVerbose("发现 service 的实现列表：");
                Iterator it = linkedHashSet.iterator();
                while (it.hasNext()) {
                    Logger.INSTANCE.logVerbose("\t\t" + ((IService) it.next()).getClass().getCanonicalName());
                }
            }
            if (linkedHashSet.isEmpty()) {
                return null;
            }
            return linkedHashSet;
        }
    }
}
