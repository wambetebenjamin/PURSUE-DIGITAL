package com.oasis.openAD;
/* loaded from: classes9.dex */
public interface OpenAdCustomListener {
    void onAdClose(int i2, String str);

    void onAdShow(int i2, String str);

    void onAdVideoBarClick(int i2, String str);

    void onDownloadActive(String str, String str2);

    void onDownloadFailed(int i2, String str, String str2);

    void onDownloadFinished(String str, String str2);

    void onDownloadPaused(String str, String str2);

    void onIdle(int i2, String str);

    void onInstalled(String str, String str2);

    void onLoadVideoError(int i2, String str, String str2);

    void onRewardVerify(int i2, String str);

    void onRewardVideoCached(int i2, String str);

    void onRewardVideoLoad(int i2, String str);

    void onSkippedVideo(int i2, String str);

    void onVideoCompleted(int i2, String str);

    void onVideoError(int i2, String str, String str2);
}
