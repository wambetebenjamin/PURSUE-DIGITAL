package com.facebook.react.bridge;

import android.os.AsyncTask;
/* loaded from: classes8.dex */
public abstract class GuardedResultAsyncTask<Result> extends AsyncTask<Void, Void, Result> {
    private final NativeModuleCallExceptionHandler mExceptionHandler;

    protected abstract Result doInBackgroundGuarded();

    protected abstract void onPostExecuteGuarded(Result result);

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public GuardedResultAsyncTask(ReactContext reactContext) {
        this(reactContext.getExceptionHandler());
    }

    protected GuardedResultAsyncTask(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mExceptionHandler = nativeModuleCallExceptionHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public final Result doInBackground(Void... voidArr) {
        try {
            return doInBackgroundGuarded();
        } catch (RuntimeException e2) {
            if (this.mExceptionHandler != null) {
                this.mExceptionHandler.handleException(e2);
            }
            throw e2;
        }
    }

    @Override // android.os.AsyncTask
    protected final void onPostExecute(Result result) {
        try {
            onPostExecuteGuarded(result);
        } catch (RuntimeException e2) {
            if (this.mExceptionHandler != null) {
                this.mExceptionHandler.handleException(e2);
            }
        }
    }
}
