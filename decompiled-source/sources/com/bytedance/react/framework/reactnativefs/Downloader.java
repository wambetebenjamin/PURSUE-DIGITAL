package com.bytedance.react.framework.reactnativefs;

import android.os.AsyncTask;
import android.os.Build;
import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
public class Downloader extends AsyncTask<DownloadParams, long[], DownloadResult> {
    private AtomicBoolean mAbort = new AtomicBoolean(false);
    private DownloadParams mParam;
    DownloadResult res;

    protected void onPostExecute(Exception exc) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public DownloadResult doInBackground(DownloadParams... downloadParamsArr) {
        this.mParam = downloadParamsArr[0];
        this.res = new DownloadResult();
        new Thread(new Runnable() { // from class: com.bytedance.react.framework.reactnativefs.Downloader.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Downloader downloader = Downloader.this;
                    downloader.download(downloader.mParam, Downloader.this.res);
                    Downloader.this.mParam.onTaskCompleted.onTaskCompleted(Downloader.this.res);
                } catch (Exception e2) {
                    Downloader.this.res.exception = e2;
                    Downloader.this.mParam.onTaskCompleted.onTaskCompleted(Downloader.this.res);
                }
            }
        }).start();
        return this.res;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0230  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void download(com.bytedance.react.framework.reactnativefs.DownloadParams r30, com.bytedance.react.framework.reactnativefs.DownloadResult r31) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.react.framework.reactnativefs.Downloader.download(com.bytedance.react.framework.reactnativefs.DownloadParams, com.bytedance.react.framework.reactnativefs.DownloadResult):void");
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        if (Build.VERSION.SDK_INT >= 24) {
            return httpURLConnection.getContentLengthLong();
        }
        return httpURLConnection.getContentLength();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stop() {
        this.mAbort.set(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(long[]... jArr) {
        super.onProgressUpdate((Object[]) jArr);
        if (this.mParam.onDownloadProgress != null) {
            this.mParam.onDownloadProgress.onDownloadProgress(jArr[0][0], jArr[0][1]);
        }
    }
}
