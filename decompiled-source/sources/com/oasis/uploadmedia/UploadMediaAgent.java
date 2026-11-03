package com.oasis.uploadmedia;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
import j.a;
/* loaded from: classes9.dex */
public abstract class UploadMediaAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static UploadMediaAgent f17766a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17767b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17768c = true;

    public UploadMediaAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static UploadMediaAgent createInstance(Context context) {
        if (f17768c || f17766a == null) {
            try {
                String string = context.getString(R.string.upload_media_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("UploadMediaAgent", "Create the UploadMediaAgent:" + string);
                    f17766a = (UploadMediaAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("UploadMediaAgent", "Create UploadMediaAgent failed.", e2);
            }
            if (f17766a == null) {
                Logger.i("UploadMediaAgent", "Create default UploadMediaAgent.");
                f17766a = new a();
                f17767b = true;
            }
            return f17766a;
        }
        throw new AssertionError();
    }

    public static UploadMediaAgent getInstance() {
        if (f17768c || f17766a != null) {
            return f17766a;
        }
        throw new AssertionError();
    }

    public void accessLocalImage(int i2, boolean z, String str, AccessImageListener accessImageListener) {
    }

    public void downLoadImage(String str, String str2, DownloadListener downloadListener) {
    }

    public void downLoadImages(String str, String str2, DownloadListener downloadListener) {
    }

    public void fastRequestReview(String str, ReviewImageListener reviewImageListener) {
    }

    public void fetchCompressImages(String str, String str2, int i2, int i3, DownloadListener downloadListener) {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17767b;
    }

    public void oneKeyUploadGameImage(int i2, boolean z, String str, UpdateCompleteListener updateCompleteListener, UploadProgressListener uploadProgressListener) {
    }

    public void uploadImage(String str, String str2, String str3, UpdateCompleteListener updateCompleteListener, UploadProgressListener uploadProgressListener) {
    }

    public void uploadImageV2(String str, UpdateCompleteListener updateCompleteListener, UploadProgressListener uploadProgressListener) {
    }
}
