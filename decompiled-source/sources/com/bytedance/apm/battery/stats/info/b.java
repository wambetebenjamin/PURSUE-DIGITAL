package com.bytedance.apm.battery.stats.info;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* compiled from: BaseHookInfo.java */
/* loaded from: classes.dex */
public abstract class b {
    public StackTraceElement[] elements;
    public long endTime;
    public long startTime;
    public String threadName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 9; i2 < this.elements.length; i2++) {
            sb.append("\tat ").append(this.elements[i2].toString()).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }
}
