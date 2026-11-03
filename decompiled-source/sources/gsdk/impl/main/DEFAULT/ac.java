package gsdk.impl.main.DEFAULT;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.loccom.api.common.LocationCommonErrorCode;
/* compiled from: LocationCommonInnerTools.java */
/* loaded from: classes10.dex */
public class ac {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20390a = "成功";

    /* renamed from: b  reason: collision with root package name */
    public static final String f20391b = "IP获取失败";

    public static GSDKError a(int i2, String str, String str2) {
        int i3 = LocationCommonErrorCode.GET_IP_FAIL;
        if (i2 == -211030) {
            i3 = LocationCommonErrorCode.GET_IP_FAIL;
        } else if (i2 == 0) {
            return new GSDKError(0, "成功");
        }
        return new GSDKError(i3, f20391b, i2, str, str2);
    }

    public static GSDKError a(int i2, String str) {
        return a(i2, str, "");
    }
}
