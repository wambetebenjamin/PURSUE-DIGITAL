package com.bytedance.pipo.iap.common.ability.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PsrOptimizeFlag.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0017\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bBK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\f\"\u0004\b\u000f\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014¨\u0006\u001c"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/PsrOptimizeFlag;", "", "isSkuLocalMock", "", "skuLocalMockErrorCode", "", "isSkuQueryRetry", "skuQueryRetryErrorCode", "isLaunchFlowRetry", "launchFlowRetryScene", "launchFlowRetryErrorCode", "(ZLjava/lang/String;ZLjava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "()Z", "setLaunchFlowRetry", "(Z)V", "setSkuLocalMock", "setSkuQueryRetry", "getLaunchFlowRetryErrorCode", "()Ljava/lang/String;", "setLaunchFlowRetryErrorCode", "(Ljava/lang/String;)V", "getLaunchFlowRetryScene", "setLaunchFlowRetryScene", "getSkuLocalMockErrorCode", "setSkuLocalMockErrorCode", "getSkuQueryRetryErrorCode", "setSkuQueryRetryErrorCode", "Companion", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PsrOptimizeFlag {
    public static final Companion Companion = new Companion(null);
    public static final String LAUNCH_FLOW_SCENE_ON_PURCHASE = "onPurchaseUpdated";
    public static final String LAUNCH_FLOW_SCENE_PAY_START = "pay_start";
    private boolean isLaunchFlowRetry;
    private boolean isSkuLocalMock;
    private boolean isSkuQueryRetry;
    private String launchFlowRetryErrorCode;
    private String launchFlowRetryScene;
    private String skuLocalMockErrorCode;
    private String skuQueryRetryErrorCode;

    public PsrOptimizeFlag() {
        this(false, null, false, null, false, null, null, 127, null);
    }

    public PsrOptimizeFlag(boolean z, String skuLocalMockErrorCode, boolean z2, String skuQueryRetryErrorCode, boolean z3, String launchFlowRetryScene, String launchFlowRetryErrorCode) {
        Intrinsics.checkNotNullParameter(skuLocalMockErrorCode, "skuLocalMockErrorCode");
        Intrinsics.checkNotNullParameter(skuQueryRetryErrorCode, "skuQueryRetryErrorCode");
        Intrinsics.checkNotNullParameter(launchFlowRetryScene, "launchFlowRetryScene");
        Intrinsics.checkNotNullParameter(launchFlowRetryErrorCode, "launchFlowRetryErrorCode");
        this.isSkuLocalMock = z;
        this.skuLocalMockErrorCode = skuLocalMockErrorCode;
        this.isSkuQueryRetry = z2;
        this.skuQueryRetryErrorCode = skuQueryRetryErrorCode;
        this.isLaunchFlowRetry = z3;
        this.launchFlowRetryScene = launchFlowRetryScene;
        this.launchFlowRetryErrorCode = launchFlowRetryErrorCode;
    }

    public /* synthetic */ PsrOptimizeFlag(boolean z, String str, boolean z2, String str2, boolean z3, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? false : z2, (i2 & 8) != 0 ? "" : str2, (i2 & 16) == 0 ? z3 : false, (i2 & 32) != 0 ? "" : str3, (i2 & 64) != 0 ? "" : str4);
    }

    public final boolean isSkuLocalMock() {
        return this.isSkuLocalMock;
    }

    public final void setSkuLocalMock(boolean z) {
        this.isSkuLocalMock = z;
    }

    public final String getSkuLocalMockErrorCode() {
        return this.skuLocalMockErrorCode;
    }

    public final void setSkuLocalMockErrorCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.skuLocalMockErrorCode = str;
    }

    public final boolean isSkuQueryRetry() {
        return this.isSkuQueryRetry;
    }

    public final void setSkuQueryRetry(boolean z) {
        this.isSkuQueryRetry = z;
    }

    public final String getSkuQueryRetryErrorCode() {
        return this.skuQueryRetryErrorCode;
    }

    public final void setSkuQueryRetryErrorCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.skuQueryRetryErrorCode = str;
    }

    public final boolean isLaunchFlowRetry() {
        return this.isLaunchFlowRetry;
    }

    public final void setLaunchFlowRetry(boolean z) {
        this.isLaunchFlowRetry = z;
    }

    public final String getLaunchFlowRetryScene() {
        return this.launchFlowRetryScene;
    }

    public final void setLaunchFlowRetryScene(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.launchFlowRetryScene = str;
    }

    public final String getLaunchFlowRetryErrorCode() {
        return this.launchFlowRetryErrorCode;
    }

    public final void setLaunchFlowRetryErrorCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.launchFlowRetryErrorCode = str;
    }

    /* compiled from: PsrOptimizeFlag.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/PsrOptimizeFlag$Companion;", "", "()V", "LAUNCH_FLOW_SCENE_ON_PURCHASE", "", "LAUNCH_FLOW_SCENE_PAY_START", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
