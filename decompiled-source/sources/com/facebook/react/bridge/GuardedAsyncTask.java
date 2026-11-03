package com.facebook.react.bridge;

import android.os.AsyncTask;
/* loaded from: classes8.dex */
public abstract class GuardedAsyncTask<Params, Progress> extends AsyncTask<Params, Progress, Void> {
    private final NativeModuleCallExceptionHandler mExceptionHandler;

    protected abstract void doInBackgroundGuarded(Params... paramsArr);

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public GuardedAsyncTask(ReactContext reactContext) {
        this(reactContext.getExceptionHandler());
    }

    protected GuardedAsyncTask(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mExceptionHandler = nativeModuleCallExceptionHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public final Void doInBackground(Params... paramsArr) {
        try {
            doInBackgroundGuarded(paramsArr);
            return null;
        } catch (RuntimeException e2) {
            try {
                if (this.mExceptionHandler != null) {
                    this.mExceptionHandler.handleException(e2);
                    return null;
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
