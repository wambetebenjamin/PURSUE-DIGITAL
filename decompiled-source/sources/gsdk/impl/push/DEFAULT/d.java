package gsdk.impl.push.DEFAULT;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.push.api.common.PushErrorCode;
/* compiled from: PushInnerTools.java */
/* loaded from: classes10.dex */
public class d {
    public static GSDKError a(int i2, String str, String str2) {
        int i3;
        String str3;
        if (i2 == -1004) {
            i3 = PushErrorCode.LOCAL_PUSH_NUM_LIMIT;
            str3 = "推送达到上限";
        } else if (i2 != -1002) {
            i3 = PushErrorCode.UNKNOWN_ERROR;
            str3 = "未知错误";
        } else {
            i3 = PushErrorCode.LOCAL_PUSH_PARAM_INVALID;
            str3 = "推送日期设置异常";
        }
        return new GSDKError(i3, str3, i2, str, str2);
    }

    public static GSDKError a(int i2, String str) {
        return a(i2, str, "");
    }
}
