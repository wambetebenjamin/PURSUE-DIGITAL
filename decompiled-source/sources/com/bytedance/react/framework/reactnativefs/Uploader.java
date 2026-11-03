package com.bytedance.react.framework.reactnativefs;

import android.os.AsyncTask;
import android.webkit.MimeTypeMap;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
public class Uploader extends AsyncTask<UploadParams, int[], UploadResult> {
    private AtomicBoolean mAbort = new AtomicBoolean(false);
    private UploadParams mParams;
    private UploadResult res;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public UploadResult doInBackground(UploadParams... uploadParamsArr) {
        this.mParams = uploadParamsArr[0];
        this.res = new UploadResult();
        new Thread(new Runnable() { // from class: com.bytedance.react.framework.reactnativefs.Uploader.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Uploader uploader = Uploader.this;
                    uploader.upload(uploader.mParams, Uploader.this.res);
                    Uploader.this.mParams.onUploadComplete.onUploadComplete(Uploader.this.res);
                } catch (Exception e2) {
                    Uploader.this.res.exception = e2;
                    Uploader.this.mParams.onUploadComplete.onUploadComplete(Uploader.this.res);
                }
            }
        }).start();
        return this.res;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x015a A[Catch: all -> 0x0206, TryCatch #4 {all -> 0x0206, blocks: (B:26:0x010f, B:33:0x0149, B:35:0x015a, B:37:0x019d, B:39:0x01a6, B:32:0x0138), top: B:134:0x010f }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void upload(com.bytedance.react.framework.reactnativefs.UploadParams r37, com.bytedance.react.framework.reactnativefs.UploadResult r38) {
        /*
            Method dump skipped, instructions count: 949
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.react.framework.reactnativefs.Uploader.upload(com.bytedance.react.framework.reactnativefs.UploadParams, com.bytedance.react.framework.reactnativefs.UploadResult):void");
    }

    protected String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        String mimeTypeFromExtension = fileExtensionFromUrl != null ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl.toLowerCase()) : null;
        return mimeTypeFromExtension == null ? "*/*" : mimeTypeFromExtension;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stop() {
        this.mAbort.set(true);
    }
}
