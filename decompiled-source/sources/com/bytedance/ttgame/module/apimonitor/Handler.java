package com.bytedance.ttgame.module.apimonitor;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import java.lang.reflect.Method;
import java.util.ArrayList;
/* loaded from: classes4.dex */
interface Handler {
    default CallingEntity parseEntity(String str) {
        if (str.contains(CertificateUtil.DELIMITER)) {
            String[] split = str.split(CertificateUtil.DELIMITER);
            return new ComponentCallingEntity(split[0], split[1], split[2]);
        }
        return new LibraryCallingEntity(str);
    }

    default Method findMethod(Class<IModuleApi> cls, String str, String[] strArr) {
        try {
            ArrayList arrayList = new ArrayList();
            for (String str2 : strArr) {
                char c2 = 65535;
                switch (str2.hashCode()) {
                    case -1325958191:
                        if (str2.equals(Constants.DOUBLE)) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case 104431:
                        if (str2.equals(Constants.INT)) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case 3039496:
                        if (str2.equals(Constants.BYTE)) {
                            c2 = 6;
                            break;
                        }
                        break;
                    case 3052374:
                        if (str2.equals(Constants.CHAR)) {
                            c2 = 7;
                            break;
                        }
                        break;
                    case 3327612:
                        if (str2.equals("long")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 64711720:
                        if (str2.equals("boolean")) {
                            c2 = 5;
                            break;
                        }
                        break;
                    case 97526364:
                        if (str2.equals("float")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 109413500:
                        if (str2.equals(Constants.SHORT)) {
                            c2 = 2;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        arrayList.add(Integer.TYPE);
                        break;
                    case 1:
                        arrayList.add(Long.TYPE);
                        break;
                    case 2:
                        arrayList.add(Short.TYPE);
                        break;
                    case 3:
                        arrayList.add(Float.TYPE);
                        break;
                    case 4:
                        arrayList.add(Double.TYPE);
                        break;
                    case 5:
                        arrayList.add(Boolean.TYPE);
                        break;
                    case 6:
                        arrayList.add(Byte.TYPE);
                        break;
                    case 7:
                        arrayList.add(Character.TYPE);
                        break;
                    default:
                        arrayList.add(Class.forName(str2));
                        break;
                }
            }
            return cls.getMethod(str, (Class[]) arrayList.toArray(new Class[0]));
        } catch (Throwable unused) {
            return null;
        }
    }
}
