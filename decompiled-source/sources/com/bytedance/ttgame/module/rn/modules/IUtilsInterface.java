package com.bytedance.ttgame.module.rn.modules;

import android.app.Activity;
import java.util.HashMap;
/* loaded from: classes6.dex */
public interface IUtilsInterface {
    void aesDecrypt(String str, IRNCallback iRNCallback);

    void aesEncrypt(String str, IRNCallback iRNCallback);

    void checkBundlePackages(IRNCallback iRNCallback);

    void clearFrescoMemory(IRNCallback iRNCallback);

    void getCommonRiskInfo(IRNCallback iRNCallback);

    void getDeviceLevel(IRNCallback iRNCallback);

    void jumpPage(HashMap hashMap, Activity activity, IRNCallback iRNCallback);

    void openAccountManagementPanel(Activity activity, IRNCallback iRNCallback);

    void openUrl(String str, Activity activity, IRNCallback iRNCallback);

    void openWebView(HashMap hashMap, Activity activity, IRNCallback iRNCallback);

    void sendMessageToUnity(String str, HashMap hashMap, String str2, Activity activity, IRNCallback iRNCallback);

    void setName(String str);

    void toast(String str, Activity activity, IRNCallback iRNCallback);
}
