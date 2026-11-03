package com.facebook.common.executors;
/* loaded from: classes8.dex */
public interface PriorityRunnable extends Runnable {
    public static final int PRIORITY_DEFAULT = 1;
    public static final int PRIORITY_HEGH = 2;
    public static final int PRIORITY_IMMEDIATE = 3;
    public static final int PRIORITY_LOW = 0;
    public static final int PRIORITY_MEDIUM = 1;

    int getPriority();
}
