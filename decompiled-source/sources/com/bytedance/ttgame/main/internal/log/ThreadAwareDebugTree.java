package com.bytedance.ttgame.main.internal.log;

import timber.log.Timber;
/* loaded from: classes3.dex */
public class ThreadAwareDebugTree extends Timber.DebugTree {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // timber.log.Timber.DebugTree, timber.log.Timber.Tree
    public void log(int i2, String str, String str2, Throwable th) {
        if (str != null) {
            str = "<" + Thread.currentThread().getName() + "> " + str;
        }
        super.log(i2, str, str2, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // timber.log.Timber.DebugTree
    public String createStackElementTag(StackTraceElement stackTraceElement) {
        return super.createStackElementTag(stackTraceElement) + "(Line " + stackTraceElement.getLineNumber() + ")";
    }
}
