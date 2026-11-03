package com.bytedance.ttgame.library.module_manager.internal;

import java.util.List;
import kotlin.Metadata;
/* compiled from: MetaDataLoader.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/MetaDataLoader;", "", "load", "", "Lcom/bytedance/ttgame/library/module_manager/internal/MetaData;", "Companion", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface MetaDataLoader {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String commonPrefix = "__gsdk_manifest";
    public static final String componentCommonPrefix = "__gsdk_manifest_component__";
    public static final String libraryCommonPrefix = "__gsdk_manifest_library__";

    List<MetaData> load();

    /* compiled from: MetaDataLoader.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/MetaDataLoader$Companion;", "", "()V", "commonPrefix", "", "componentCommonPrefix", "libraryCommonPrefix", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String commonPrefix = "__gsdk_manifest";
        public static final String componentCommonPrefix = "__gsdk_manifest_component__";
        public static final String libraryCommonPrefix = "__gsdk_manifest_library__";

        private Companion() {
        }
    }
}
