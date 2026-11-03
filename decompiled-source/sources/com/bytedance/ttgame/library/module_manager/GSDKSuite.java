package com.bytedance.ttgame.library.module_manager;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GSDKSuite.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001Bm\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\n\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\n¢\u0006\u0002\u0010\u0010R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012¨\u0006\u001c"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/GSDKSuite;", "", "region", "Lcom/bytedance/ttgame/library/module_manager/GSDKRegion;", "suiteFamily", "", "suiteRevision", "suitePublicVersion", "gsdkAppId", "components", "", "Lcom/bytedance/ttgame/library/module_manager/Component;", "libraries", "Lcom/bytedance/ttgame/library/module_manager/Library;", "wrappers", "Lcom/bytedance/ttgame/library/module_manager/GSDKWrapper;", "(Lcom/bytedance/ttgame/library/module_manager/GSDKRegion;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "getComponents", "()Ljava/util/Map;", "getGsdkAppId", "()Ljava/lang/String;", "getLibraries", "getRegion", "()Lcom/bytedance/ttgame/library/module_manager/GSDKRegion;", "getSuiteFamily", "getSuitePublicVersion", "getSuiteRevision", "getWrappers", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GSDKSuite {
    private final Map<String, Component> components;
    private final String gsdkAppId;
    private final Map<String, Library> libraries;
    private final GSDKRegion region;
    private final String suiteFamily;
    private final String suitePublicVersion;
    private final String suiteRevision;
    private final Map<String, GSDKWrapper> wrappers;

    public GSDKSuite(GSDKRegion region, String suiteFamily, String suiteRevision, String str, String gsdkAppId, Map<String, Component> components, Map<String, Library> libraries, Map<String, GSDKWrapper> wrappers) {
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(suiteFamily, "suiteFamily");
        Intrinsics.checkNotNullParameter(suiteRevision, "suiteRevision");
        Intrinsics.checkNotNullParameter(gsdkAppId, "gsdkAppId");
        Intrinsics.checkNotNullParameter(components, "components");
        Intrinsics.checkNotNullParameter(libraries, "libraries");
        Intrinsics.checkNotNullParameter(wrappers, "wrappers");
        this.region = region;
        this.suiteFamily = suiteFamily;
        this.suiteRevision = suiteRevision;
        this.suitePublicVersion = str;
        this.gsdkAppId = gsdkAppId;
        this.components = components;
        this.libraries = libraries;
        this.wrappers = wrappers;
    }

    public final GSDKRegion getRegion() {
        return this.region;
    }

    public final String getSuiteFamily() {
        return this.suiteFamily;
    }

    public final String getSuiteRevision() {
        return this.suiteRevision;
    }

    public final String getSuitePublicVersion() {
        return this.suitePublicVersion;
    }

    public final String getGsdkAppId() {
        return this.gsdkAppId;
    }

    public final Map<String, Component> getComponents() {
        return this.components;
    }

    public final Map<String, Library> getLibraries() {
        return this.libraries;
    }

    public final Map<String, GSDKWrapper> getWrappers() {
        return this.wrappers;
    }
}
