package com.bytedance.crash;

import java.util.Map;
/* loaded from: classes2.dex */
public interface AttachUserData {
    Map<? extends String, ? extends String> getUserData(CrashType crashType);
}
