package com.oasis.upgrade;

import android.app.Activity;
import com.oasis.Logger.Logger;
import com.oasis.android.ICommonCallbackWithJudge;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class DefaultUpgradeAgent extends UpgradeAgent {
    @Override // com.oasis.upgrade.UpgradeAgent
    public void cancelExecution(long j2) {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void cancelUpgrade() {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void continueExecution(long j2) {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void overwriteInstallApk(String str) {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void queryForOwnRule() {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void restartApp() {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void setCustomUIUpgradeListener(CustomUIUpgradeListener customUIUpgradeListener) {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void setOwnRuleRequestCallback(ICommonCallbackWithJudge iCommonCallbackWithJudge) {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void setUpgradeCallback(UpgradeListener upgradeListener) {
        Logger.i("Oasis.upgrade", "DefaultPushAgent.setUpgradeCallback");
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void setUpgradeCallbackV2(UpgradeListenerV2 upgradeListenerV2) {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void setUpgradeForOwnRuleCallback(IUpgradeForOwnRuleCallback iUpgradeForOwnRuleCallback, ITaskProgressListener iTaskProgressListener) {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void startForceUpgrade() {
        Logger.i("Oasis.upgrade", "DefaultPushAgent.startForceUpgrade");
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void startForceUpgradeV2() {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void startUpgradeForOwnRule(Activity activity, String str) {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void startUpgradeForOwnRule(String str) {
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void tryForceUpgrade() {
        Logger.i("Oasis.upgrade", "DefaultPushAgent.tryForceUpgrade");
    }

    @Override // com.oasis.upgrade.UpgradeAgent
    public void tryForceUpgradeV2() {
    }
}
