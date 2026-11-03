package com.facebook.react.bridge.queue;
/* loaded from: classes8.dex */
public class MessageQueueThreadSpec {
    public static final long DEFAULT_STACK_SIZE_BYTES = 0;
    private static final MessageQueueThreadSpec MAIN_UI_SPEC = new MessageQueueThreadSpec(ThreadType.MAIN_UI, "main_ui");
    private final String mName;
    private final long mStackSize;
    private final ThreadType mThreadType;

    /* loaded from: classes8.dex */
    protected enum ThreadType {
        MAIN_UI,
        NEW_BACKGROUND
    }

    public static MessageQueueThreadSpec newUIBackgroundTreadSpec(String str) {
        return new MessageQueueThreadSpec(ThreadType.NEW_BACKGROUND, str);
    }

    public static MessageQueueThreadSpec newBackgroundThreadSpec(String str) {
        return new MessageQueueThreadSpec(ThreadType.NEW_BACKGROUND, str);
    }

    public static MessageQueueThreadSpec newBackgroundThreadSpec(String str, long j2) {
        return new MessageQueueThreadSpec(ThreadType.NEW_BACKGROUND, str, j2);
    }

    public static MessageQueueThreadSpec mainThreadSpec() {
        return MAIN_UI_SPEC;
    }

    private MessageQueueThreadSpec(ThreadType threadType, String str) {
        this(threadType, str, 0L);
    }

    private MessageQueueThreadSpec(ThreadType threadType, String str, long j2) {
        this.mThreadType = threadType;
        this.mName = str;
        this.mStackSize = j2;
    }

    public ThreadType getThreadType() {
        return this.mThreadType;
    }

    public String getName() {
        return this.mName;
    }

    public long getStackSize() {
        return this.mStackSize;
    }
}
