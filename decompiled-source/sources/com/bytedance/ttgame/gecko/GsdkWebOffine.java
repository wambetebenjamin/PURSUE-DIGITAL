package com.bytedance.ttgame.gecko;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.bytedance.falconx.f;
import com.bytedance.falconx.g;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/* loaded from: classes3.dex */
public class GsdkWebOffine {
    private f mFalconWebOffline;

    public GsdkWebOffine(Context context, String str, String str2, List<Pattern> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Uri.fromFile(new File(GsdkGeckoConfig.offlineRootDir(context))));
        this.mFalconWebOffline = new f(new g.a(context).e(str).b(GsdkGeckoConfig.host()).c(GsdkGeckoConfig.getGeckoAdAccessKey()).a(list).b(arrayList).d(str2).a());
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return this.mFalconWebOffline.a(webView, str);
    }

    public boolean isEnable() {
        return this.mFalconWebOffline.a();
    }

    public void setEnable(boolean z) {
        this.mFalconWebOffline.a(z);
    }

    public void release() {
        this.mFalconWebOffline.b();
    }
}
