package com.bytedance.ug.sdk.share.impl.callback;
/* compiled from: VideoDialogCallback.java */
/* loaded from: classes8.dex */
public interface f {

    /* compiled from: VideoDialogCallback.java */
    /* loaded from: classes8.dex */
    public static abstract class a implements f {
        @Override // com.bytedance.ug.sdk.share.impl.callback.f
        public boolean skipDownloadVideo() {
            return false;
        }

        @Override // com.bytedance.ug.sdk.share.impl.callback.f
        public boolean skipVideoShareDialog() {
            return false;
        }
    }

    boolean skipDownloadVideo();

    boolean skipVideoShareDialog();
}
