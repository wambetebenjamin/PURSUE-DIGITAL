package com.ttnet.org.chromium.net.urlconnection;

import com.ss.android.ugc.quota.tag.a;
import java.io.IOException;
/* loaded from: classes3.dex */
public class TTRequestCanceledException extends IOException {
    public int getCronetInternalErrorCode() {
        return a.UNSPECIFIED;
    }

    public TTRequestCanceledException(String str) {
        super(str);
    }
}
