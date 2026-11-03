package com.facebook.drawee.interfaces;

import android.net.Uri;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public interface SimpleDraweeControllerBuilder {
    DraweeController build();

    SimpleDraweeControllerBuilder setCallerContext(Object obj);

    SimpleDraweeControllerBuilder setOldController(@Nullable DraweeController draweeController);

    SimpleDraweeControllerBuilder setUri(Uri uri);

    SimpleDraweeControllerBuilder setUri(Uri uri, String str, String str2);

    SimpleDraweeControllerBuilder setUri(@Nullable String str);

    SimpleDraweeControllerBuilder setUris(List<Uri> list);
}
