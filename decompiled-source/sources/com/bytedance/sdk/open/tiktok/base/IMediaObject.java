package com.bytedance.sdk.open.tiktok.base;

import android.os.Bundle;
/* loaded from: classes3.dex */
public interface IMediaObject {
    public static final int TYPE_CONTACT_HTML = 5;
    public static final int TYPE_CONTACT_IMAGE = 4;
    public static final int TYPE_IMAGE = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;

    boolean checkArgs();

    void serialize(Bundle bundle);

    int type();

    void unserialize(Bundle bundle);
}
