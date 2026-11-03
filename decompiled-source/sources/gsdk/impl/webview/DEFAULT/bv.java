package gsdk.impl.webview.DEFAULT;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.webview.api.common.WebViewErrorCode;
/* compiled from: WebViewInnerTools.java */
/* loaded from: classes10.dex */
public class bv {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21037a = "SUCCESS";

    /* renamed from: b  reason: collision with root package name */
    public static final String f21038b = "url为空";

    /* renamed from: c  reason: collision with root package name */
    public static final String f21039c = "url格式错误";

    /* renamed from: d  reason: collision with root package name */
    public static final String f21040d = "未知错误";

    public static GSDKError a(int i2, String str, String str2) {
        int i3;
        String str3;
        if (i2 == 0) {
            return new GSDKError(0, f21037a);
        }
        switch (i2) {
            case -140002:
                i3 = -140002;
                str3 = f21039c;
                break;
            case -140001:
                i3 = -140001;
                str3 = f21038b;
                break;
            default:
                i3 = WebViewErrorCode.UNKNOWN_ERROR;
                str3 = "未知错误";
                break;
        }
        return new GSDKError(i3, str3, i2, str, str2);
    }
}
