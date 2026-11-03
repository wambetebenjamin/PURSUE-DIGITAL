package com.google.android.material.timepicker;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes6.dex */
interface TimePickerControls {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface ActiveSelection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface ClockPeriod {
    }

    void setActiveSelection(int i2);

    void setHandRotation(float f2);

    void setValues(String[] strArr, int i2);

    void updateTime(int i2, int i3, int i4);
}
