package com.oasis.upgrade;
/* loaded from: classes9.dex */
public interface IUpgradeForOwnRuleCallback {
    void onDownloadFinished(String str);

    void onHotUpdateInstalled();

    void onPrepareToDownload(boolean z, long j2, long j3);

    void onPrepareToHotUpdateInstall(int i2, long j2);

    void onPrepareToSynthesize(long j2);

    void onReadyToOverwriteInstall(String str);

    void onSynthesizeFinished(String str);

    void onTaskStart();

    void onTaskStop(int i2, String str, int i3, String str2);
}
