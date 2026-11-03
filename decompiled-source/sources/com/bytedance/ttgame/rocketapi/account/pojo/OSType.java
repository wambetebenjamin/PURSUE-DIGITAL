package com.bytedance.ttgame.rocketapi.account.pojo;

import com.bytedance.bdturing.utils.b;
/* loaded from: classes7.dex */
public enum OSType {
    OSTypeIOS("iOS"),
    OSTypeAndroid(b.f4370d),
    OSTypeWindows("PC"),
    OSTypeMacOS("MacOS");
    
    private String osType;

    OSType(String str) {
        this.osType = str;
    }

    public String getOsType() {
        return this.osType;
    }
}
