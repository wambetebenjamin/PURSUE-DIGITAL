package com.bytedance.ttgame.module.rn.modules;

import android.app.Activity;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Map;
/* loaded from: classes6.dex */
public interface IEnvironmentInterface {
    void getCommonInfo(IRNCallback iRNCallback);

    void getCommonParams(IRNCallback iRNCallback);

    Map getConstants();

    void getEnv(IRNCallback iRNCallback);

    void getGameRoleAndServer(IRNCallback iRNCallback);

    void getSecretUid(IRNCallback iRNCallback);

    void getUserInfo(IRNCallback iRNCallback);

    void isEmulator(IRNCallback iRNCallback);

    void isLogin(IRNCallback iRNCallback);

    void setModuleName(String str);

    void showDebugOption(Activity activity, ReactApplicationContext reactApplicationContext, IRNCallback iRNCallback);
}
