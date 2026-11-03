package com.facebook.imagepipeline.common;

import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public enum Priority {
    LOW,
    MEDIUM,
    HIGH,
    IMMEDIATE;

    public static Priority getHigherPriority(@Nullable Priority priority, @Nullable Priority priority2) {
        return priority == null ? priority2 : (priority2 != null && priority.ordinal() <= priority2.ordinal()) ? priority2 : priority;
    }

    public static Priority getLowerPriority(@Nullable Priority priority, @Nullable Priority priority2) {
        return priority == null ? priority2 : (priority2 != null && priority.ordinal() >= priority2.ordinal()) ? priority2 : priority;
    }

    /* renamed from: com.facebook.imagepipeline.common.Priority$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$common$Priority;

        static {
            int[] iArr = new int[Priority.values().length];
            $SwitchMap$com$facebook$imagepipeline$common$Priority = iArr;
            try {
                iArr[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$common$Priority[Priority.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$common$Priority[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$common$Priority[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static int getIntPriorityValue(Priority priority) {
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$imagepipeline$common$Priority[priority.ordinal()];
        if (i2 != 1) {
            if (i2 != 3) {
                return i2 != 4 ? 1 : 3;
            }
            return 2;
        }
        return 0;
    }
}
