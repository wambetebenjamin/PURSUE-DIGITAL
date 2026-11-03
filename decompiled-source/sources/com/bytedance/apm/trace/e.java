package com.bytedance.apm.trace;
/* compiled from: TraceConfig.java */
@Deprecated
/* loaded from: classes.dex */
public class e {
    public static final String KEY_FRAGMENT_CREATE_TO_VIEW_SHOW_TIME = "fragmentOnCreateToViewShow";
    public static final String KEY_FRAGMENT_HIDECHANGED_TO_VIEW_SHOW_TIME = "fragmentOnHiddenChangedToViewShow";
    public static final String KEY_FRAGMENT_VISIBLEHINT_TO_VIEW_SHOW_TIME = "fragmentUserVisibleToViewShow";
    public static final String KEY_PAGE_LOAD_TO_VIEW_SHOW_TIME = "activityOnCreateToViewShow";

    /* renamed from: b  reason: collision with root package name */
    private boolean f1536b;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1538d;

    /* renamed from: a  reason: collision with root package name */
    private long f1535a = 30000;

    /* renamed from: c  reason: collision with root package name */
    private int f1537c = 1000;

    public e setAnnotationPageTimeTraceSwitch(boolean z) {
        this.f1536b = z;
        return this;
    }

    public e setMaxWaitViewShowTime(long j2) {
        this.f1535a = j2;
        return this;
    }

    public e setBlockThresholdMs(int i2) {
        this.f1537c = i2;
        return this;
    }

    public e setDropFrameReportStackSwitch(boolean z) {
        this.f1538d = z;
        return this;
    }

    public boolean isDropFrameReportEvilMethodSwitch() {
        return this.f1538d;
    }

    @Deprecated
    public e setSwitch(boolean z) {
        return setAnnotationPageTimeTraceSwitch(z);
    }

    public long getMaxWaitViewShowTimeMs() {
        return this.f1535a;
    }

    public boolean isPageAnnotationTraceTimeSwitchOn() {
        return this.f1536b;
    }

    public int getEvilMethodThresholdMs() {
        return this.f1537c;
    }
}
