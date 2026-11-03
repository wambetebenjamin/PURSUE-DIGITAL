package com.bytedance.ttgame.core.monitor;

import com.bytedance.apm.constant.d;
import com.bytedance.common.utility.o;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class TTNetHelper {
    public static void tryAddCronetErrCode(Throwable th, JSONObject jSONObject) {
        if (th != null && jSONObject != null) {
            try {
                String message = th.getMessage();
                if (o.isEmpty(message)) {
                    return;
                }
                int length = message.length();
                int indexOf = message.indexOf("ErrorCode=");
                if (indexOf != -1) {
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = indexOf + 10; i2 < length; i2++) {
                        char charAt = message.charAt(i2);
                        if (!Character.isSpaceChar(charAt)) {
                            if (charAt != '-' && !Character.isDigit(charAt)) {
                                break;
                            }
                            sb.append(charAt);
                        }
                    }
                    if (sb.length() > 0) {
                        jSONObject.put(d.CRONET_ERROR_CODE, sb.toString());
                    }
                }
                int indexOf2 = message.indexOf("InternalErrorCode=");
                if (indexOf2 == -1) {
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                for (int i3 = indexOf2 + 18; i3 < length; i3++) {
                    char charAt2 = message.charAt(i3);
                    if (!Character.isSpaceChar(charAt2)) {
                        if (charAt2 != '-' && !Character.isDigit(charAt2)) {
                            break;
                        }
                        sb2.append(charAt2);
                    }
                }
                if (sb2.length() <= 0) {
                    return;
                }
                jSONObject.put(d.CRONET_INTERNAL_ERROR_CODE, sb2.toString());
            } catch (Throwable unused) {
            }
        }
    }
}
