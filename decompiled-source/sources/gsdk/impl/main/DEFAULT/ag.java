package gsdk.impl.main.DEFAULT;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.notice.NoticeService;
import com.bytedance.ttgame.module.notice.api.common.NoticeErrorCode;
/* compiled from: NoticeInnerTools.java */
/* loaded from: classes10.dex */
public class ag {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20405a = "success";

    /* renamed from: b  reason: collision with root package name */
    public static final String f20406b = "网络错误";

    /* renamed from: c  reason: collision with root package name */
    public static final String f20407c = "必填参数错误";

    /* renamed from: d  reason: collision with root package name */
    public static final String f20408d = "服务端错误";

    /* renamed from: e  reason: collision with root package name */
    public static final String f20409e = "未知错误";

    public static GSDKError a(int i2, String str, String str2) {
        int i3;
        String str3;
        if (i2 == -5000) {
            i3 = NoticeErrorCode.SERVER_ERROR;
            str3 = f20408d;
        } else if (i2 != 0) {
            switch (i2) {
                case NoticeService.ERROR_PARAM /* -80001 */:
                    i3 = NoticeErrorCode.PARAM_ERROR;
                    str3 = f20407c;
                    break;
                case NoticeService.ERROR_NETWORK /* -80000 */:
                    i3 = NoticeErrorCode.NETWORK_ERROR;
                    str3 = f20406b;
                    break;
                default:
                    i3 = NoticeErrorCode.UNKNOWN_ERROR;
                    str3 = "未知错误";
                    break;
            }
        } else {
            i3 = 0;
            str3 = "success";
        }
        return new GSDKError(i3, str3, i2, str, str2);
    }
}
