package com.bytedance.common.model;

import android.text.TextUtils;
/* compiled from: ProcessEnum.java */
/* loaded from: classes2.dex */
public enum b {
    MAIN(":main"),
    PUSH(":push"),
    PUSH_SERVICE(":pushservice"),
    SMP(":smp"),
    UNKNOWN(":unknown");
    
    public String processSuffix;

    b(String str) {
        this.processSuffix = str;
    }

    public static b parseProcess(String str, String str2) {
        if (TextUtils.equals(str2, str)) {
            return MAIN;
        }
        b bVar = PUSH;
        if (str.endsWith(bVar.processSuffix)) {
            return bVar;
        }
        b bVar2 = PUSH_SERVICE;
        if (str.endsWith(bVar2.processSuffix)) {
            return bVar2;
        }
        b bVar3 = SMP;
        return str.endsWith(bVar3.processSuffix) ? bVar3 : UNKNOWN;
    }

    public static b parseProcess(String str) {
        b bVar = MAIN;
        if (TextUtils.equals(str, bVar.processSuffix)) {
            return bVar;
        }
        b bVar2 = PUSH;
        if (TextUtils.equals(str, bVar2.processSuffix)) {
            return bVar2;
        }
        b bVar3 = PUSH_SERVICE;
        if (TextUtils.equals(str, bVar3.processSuffix)) {
            return bVar3;
        }
        b bVar4 = SMP;
        return TextUtils.equals(str, bVar4.processSuffix) ? bVar4 : UNKNOWN;
    }
}
