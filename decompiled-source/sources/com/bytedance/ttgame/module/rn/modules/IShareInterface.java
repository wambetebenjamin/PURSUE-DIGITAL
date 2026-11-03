package com.bytedance.ttgame.module.rn.modules;

import android.app.Activity;
import java.util.HashMap;
/* loaded from: classes6.dex */
public interface IShareInterface {
    void getBindCode(IRNCallback iRNCallback);

    void setName(String str);

    void share(HashMap hashMap, Activity activity, IRNCallback iRNCallback);

    void shareBehind(HashMap hashMap, Activity activity, IRNCallback iRNCallback);

    boolean shareBehindEnable();

    void shareContent(HashMap hashMap, boolean z, Activity activity, IRNCallback iRNCallback);
}
