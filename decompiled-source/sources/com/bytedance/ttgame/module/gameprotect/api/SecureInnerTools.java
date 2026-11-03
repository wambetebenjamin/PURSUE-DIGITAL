package com.bytedance.ttgame.module.gameprotect.api;

import android.text.TextUtils;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.gameprotect.api.common.SecureErrorCode;
/* loaded from: classes6.dex */
public class SecureInnerTools {
    public static final String DECODE_BASE64_ERROR_MSG = "base64解析失败";
    public static final String INSTANCE_PACKET_ERROR_MSG = "未open初始化BDGPGamePacketChannel";
    public static final String MALLOC__PACKET_ERROR_MSG = "gp存储服务端数据本地存储malloc失败";
    public static final String SUCCESS_MSG = "成功";
    public static final String UNKNOWN_ERROR_MSG = "未知错误";
    public static final String WRITE_PACKET_ERROR_MSG = "包异常，不能被正确解析";

    public static GSDKError convert(int i2, String str, String str2) {
        String str3;
        int i3;
        int i4;
        String str4;
        if (i2 != -1010) {
            if (i2 != -4) {
                if (i2 != -3) {
                    if (i2 != -2) {
                        if (i2 != -1) {
                            if (i2 == 0) {
                                return new GSDKError(0, "成功");
                            }
                            switch (i2) {
                                case -231004:
                                    break;
                                case -231003:
                                    break;
                                case -231002:
                                    break;
                                case -231001:
                                    break;
                                default:
                                    i4 = SecureErrorCode.UNKNOWN_ERROR;
                                    str4 = "未知错误";
                                    break;
                            }
                            i3 = i4;
                            str3 = str4;
                        }
                        i4 = SecureErrorCode.WRITE_PACKET_ERROR;
                        str4 = WRITE_PACKET_ERROR_MSG;
                        i3 = i4;
                        str3 = str4;
                    }
                    i4 = SecureErrorCode.MALLOC__PACKET_ERROR;
                    str4 = MALLOC__PACKET_ERROR_MSG;
                    i3 = i4;
                    str3 = str4;
                }
                i4 = SecureErrorCode.INSTANCE_PACKET_ERROR;
                str4 = INSTANCE_PACKET_ERROR_MSG;
                i3 = i4;
                str3 = str4;
            }
            i4 = SecureErrorCode.DECODE_BASE64_ERROR;
            str4 = DECODE_BASE64_ERROR_MSG;
            i3 = i4;
            str3 = str4;
        } else {
            str3 = str;
            i3 = -232101;
        }
        return new GSDKError(i3, str3, i2, TextUtils.isEmpty(str) ? str3 : str, str2);
    }

    public static GSDKError convert(int i2, String str) {
        return convert(i2, str, "");
    }
}
